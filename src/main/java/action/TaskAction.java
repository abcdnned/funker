package action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.Servicee;

/**
 * The type TaskAction.
 *
 * @version 1.0
 */
@Controller
public final class TaskAction {

    @Autowired
    private Servicee service;

    @RequestMapping("/")
    public ModelAndView homepage() {
        return new ModelAndView("funker");
    }

    @RequestMapping("/task")
    public ModelAndView task() {
        ModelAndView mav = new ModelAndView("task");
        mav.getModel().put("data", service.findAllTasks());
        return mav;
    }


}
