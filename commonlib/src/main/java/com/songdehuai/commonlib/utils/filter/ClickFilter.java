package com.songdehuai.commonlib.utils.filter;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ClickFilter {

    private static Long sLastclick = 0L;
    private static final Long FILTER_TIMEM = 1000L;
    private boolean canDoubleClick = false;

    @Pointcut("execution(@ com.songdehuai.commonlib.utils.filter.QuickClick * *(..))")
    public void canFastClick() {

    }

    @Before("canFastClick()")
    public void beforeEnableDoubleClcik(JoinPoint joinPoint) throws Throwable {
        canDoubleClick = true;
    }

    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    public void clickFilterHook(ProceedingJoinPoint joinPoint) {
        if (System.currentTimeMillis() - sLastclick >= FILTER_TIMEM || canDoubleClick) {
            sLastclick = System.currentTimeMillis();
            try {
                joinPoint.proceed();
                canDoubleClick = false;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } else {
            Log.e("ClickFilter", "重复点击,已过滤");
        }
    }
}