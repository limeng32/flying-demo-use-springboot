package indi.demo.flying.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import indi.demo.flying.condition.AccountCondition;
import indi.demo.flying.pojo.Account_;
import indi.demo.flying.service.AccountService;

@RestController
@EnableAutoConfiguration
@ComponentScan({ "indi.demo.flying.config", "indi.demo.flying.service" })
public class MainController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/getAccount", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Account_ account2(@RequestParam("id") Integer id) {
		Account_ e = accountService.select(id);
		return e;
	}

	@RequestMapping(value = "/getAccountById1OrId2", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Collection<Account_> account2(@RequestParam("id1") Integer id1, @RequestParam("id2") Integer id2) {
		AccountCondition ac = new AccountCondition();
		ac.setIdOr(id1, id2);
		Collection<Account_> c = accountService.selectAll(ac);
		return c;
	}

	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET }, value = "/error/{errorName}")
	public String error(HttpServletRequest request, @PathVariable("errorName") String errorName) {
		return "error:" + errorName;
	}
}