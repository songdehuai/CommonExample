
package okgo.request;

import okgo.model.HttpMethod;
import okgo.request.base.BodyRequest;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：2016/1/16
 * 描    述：Options请求
 * 修订历史：
 * ================================================
 */
public class OptionsRequest<T> extends BodyRequest<T, OptionsRequest<T>> {

    public OptionsRequest(String url) {
        super(url);
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.OPTIONS;
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        Request.Builder requestBuilder = generateRequestBuilder(requestBody);
        return requestBuilder.method("OPTIONS", requestBody).url(url).tag(tag).build();
    }
}
