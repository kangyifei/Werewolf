package com.xjtu.kangy.WereWolf.utils

import org.apache.mina.core.service.IoHandlerAdapter
import org.apache.mina.core.session.IdleStatus
import org.apache.mina.core.session.IoSession

/**
 * Created by kangy on 2017/9/5.
 */
class ServerHandler : IoHandlerAdapter() {
    override fun messageReceived(session: IoSession?, message: Any?) {
        val res = message.toString()
        var ioSessions = session?.service?.managedSessions?.values
    }

    override fun sessionOpened(session: IoSession?) {
        super.sessionOpened(session)
    }

    override fun sessionClosed(session: IoSession?) {
        super.sessionClosed(session)
    }

    override fun messageSent(session: IoSession?, message: Any?) {
        super.messageSent(session, message)
    }

    override fun inputClosed(session: IoSession?) {
        super.inputClosed(session)
    }

    override fun sessionCreated(session: IoSession?) {
        super.sessionCreated(session)
    }

    override fun sessionIdle(session: IoSession?, status: IdleStatus?) {
        super.sessionIdle(session, status)
    }

    override fun exceptionCaught(session: IoSession?, cause: Throwable?) {
        super.exceptionCaught(session, cause)
    }
}