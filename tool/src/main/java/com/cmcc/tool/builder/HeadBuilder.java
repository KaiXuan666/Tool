package com.cmcc.tool.builder;

import com.cmcc.tool.OkHttpUtils;
import com.cmcc.tool.request.OtherRequest;
import com.cmcc.tool.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers).build();
    }
}
