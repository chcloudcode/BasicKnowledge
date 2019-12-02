package com.server.Basic.IO.Netty.GroupChat;

import com.server.Utils.Common.DateTimes;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    // 定义一个 全局 的 channel组，管理所有客户端连接的channel
    //GlobalEventExecutor.INSTANCE 是全局的事件执行器，单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * handlerAdded 当客户端连接时，第一个执行的方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //先通知 其他客户端 ，当前客户端加入群聊
        //channelGroup.writeAndFlush 会遍历所有的channel，并发送消息
        channelGroup.writeAndFlush("[客户端] "+channel.remoteAddress()+" 加入群聊！ "+ DateTimes.now()+"\n");

        //加入到群聊
        channelGroup.add(channel);
    }

    /**
     * 表示channel 处于活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+" 上线了！"+DateTimes.now()+"\n");
    }

    /**
     * 表示channel 处于非活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+" 下线了！"+DateTimes.now()+"\n");
    }

    /**
     * 表示 channel 断开连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端] "+channel.remoteAddress()+" 离开群聊！"+DateTimes.now()+"\n");

        //该方法执行完后，channelGroup 会自动去掉 断开连接的 当前channle
        System.out.println("当前群聊人数："+ channelGroup.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {

        //获取当前channel
        Channel channel = ctx.channel();

        //转发客户端发送的消息到 其他客户端
        channelGroup.forEach(each->{
            if(each!=channel){
                each.writeAndFlush("[客户] "+channel.remoteAddress()+" 发送消息："+s+"\n");
            }else{
                each.writeAndFlush("[自己]"+channel.remoteAddress()+" 发送消息："+s+"\n");
            }
        });
    }

    /**
     * 出现异常的捕获
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道
        ctx.close();
    }
}
