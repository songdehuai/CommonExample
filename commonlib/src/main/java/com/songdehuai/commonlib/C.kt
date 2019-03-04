import com.songdehuai.commonlib.task.TaskCallback
import com.songdehuai.commonlib.task.TaskExecutor
import com.songdehuai.commonlib.task.imp.TaskExecutorImp

object C {

    private var httpTask: TaskExecutor = TaskExecutorImp()

    fun test(callbcck: TaskCallback.CommonCallback) {
        httpTask.start(callbcck)
    }
}
