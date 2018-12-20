package com.hatim.basics.springboot.conf;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hatim.basics.springboot.conf.StateMachineConfig.Events;
import com.hatim.basics.springboot.conf.StateMachineConfig.States;

@Controller
public class StateMachineController {

	@Autowired
	private StateMachine<States, Events> stateMachine;

	@RequestMapping("/")
	public String greeting() {
		return "redirect:/states";
	}

	@RequestMapping("/states")
	public String getStates(HttpSession session, @RequestParam(value = "event", required = false) Events event, Model model) {
		
		System.out.println( null != session ? session.getId() : "No-session" );
		
		if (event != null) {
			stateMachine.sendEvent(event);
		}
		model.addAttribute("states", stateMachine.getState().getIds());
		//model.addAttribute("stateChartModel", stateChartModel);
		return "states";
	}

}