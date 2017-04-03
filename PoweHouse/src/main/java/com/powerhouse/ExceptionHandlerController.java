package com.powerhouse;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Controller
public class ExceptionHandlerController extends AbstractHandlerExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
			Object exHandller, Exception exception) {

		try {
			if (exception instanceof MySQLIntegrityConstraintViolationException) {
				return handleDBError((MySQLIntegrityConstraintViolationException) exception, response, exHandller);
			}

		} catch (Exception handlerException) {

		}

		return null;
	}

	private ModelAndView handleDBError(MySQLIntegrityConstraintViolationException exception,
			HttpServletResponse response, Object exHandller) throws SQLException, IOException {
		
		//set some error messages for duplicated data
		return new ModelAndView();
	}

}
