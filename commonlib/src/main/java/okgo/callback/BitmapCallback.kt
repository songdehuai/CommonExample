
package okgo.callback

import android.graphics.Bitmap
import android.widget.ImageView

import okgo.convert.BitmapConvert
import okhttp3.Response

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：2016/1/12
 * 描    述：返回图片的Bitmap，这里没有进行图片的缩放，可能会发生 OOM
 * 修订历史：
 * ================================================
 */
abstract class BitmapCallback : AbsCallback<Bitmap> {

    private var convert: BitmapConvert? = null

    constructor() {
        convert = BitmapConvert()
    }

    constructor(maxWidth: Int, maxHeight: Int) {
        convert = BitmapConvert(maxWidth, maxHeight)
    }

    constructor(
        maxWidth: Int,
        maxHeight: Int,
        decodeConfig: Bitmap.Config,
        scaleType: ImageView.ScaleType
    ) {
        convert = BitmapConvert(maxWidth, maxHeight, decodeConfig, scaleType)
    }

    @Throws(Throwable::class)
    override fun convertResponse(response: Response): Bitmap {
        val bitmap = convert!!.convertResponse(response)
        response.close()
        return bitmap
    }
}
