package com.songdehuai.commonexample


class AudioPlayer {
    //定义一个内部类
    inner class ListenerBuilder {
        //定义三个回调方法
        internal var mAudioPlayAction: ((String) -> Unit)? = null
        internal var mAudioPauseAction: ((String) -> Unit)? = null
        internal var mAudioFinishAction: ((String) -> Unit)? = null
        fun onAudioPlay(action: (String) -> Unit) {
            mAudioPlayAction = action
        }

        fun onAudioPause(action: (String) -> Unit) {
            mAudioPauseAction = action
        }

        fun onAudioFinish(action: (String) -> Unit) {
            mAudioFinishAction = action
        }
    }

    lateinit var mListener: ListenerBuilder
    //提供方法供外部实现接口的回调监听
    fun registerListener(listenerBuilder: ListenerBuilder.() -> Unit) {
        mListener = ListenerBuilder().also(listenerBuilder)
    }
}
