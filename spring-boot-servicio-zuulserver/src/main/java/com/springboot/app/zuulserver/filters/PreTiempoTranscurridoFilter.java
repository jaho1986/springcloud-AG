package com.springboot.app.zuulserver.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext rc = RequestContext.getCurrentContext();
		HttpServletRequest hsr = rc.getRequest();
		Long tiempoInicio = System.currentTimeMillis();
		hsr.setAttribute("tiempoinicio", tiempoInicio);
		return null;
	}

	@Override
	public String filterType() {
		// This is where you assign the type of filter:
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}
	
}
