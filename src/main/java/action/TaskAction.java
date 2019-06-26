package action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.EnergyService;
import java.io.IOException;

/**
 * The type TaskAction.
 *
 * @version 1.0
 */
@Controller
public final class TaskAction {

    @Autowired
    private EnergyService service;

    @RequestMapping("/")
    public ModelAndView homepage() throws IOException {
        final ModelAndView mav = new ModelAndView("energy");
        final int energy = service.getEnergy();
        mav.getModel().put("data", energy);
        return mav;
    }

    @RequestMapping("/cc")
    public ModelAndView caculatePage() throws IOException {
        final ModelAndView mav = new ModelAndView("cc");
        return mav;
    }

    @RequestMapping("/init")
    public ModelAndView init() throws IOException {
        service.initEnergy();
        final ModelAndView mav = new ModelAndView("energy");
        final int energy = service.getEnergy();
        mav.getModel().put("data", energy);
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add(
            @RequestParam(required = true, defaultValue = "0") final int v) throws IOException {
        service.addEnergy(v);
        final ModelAndView mav = new ModelAndView("energy");
        final int energy = service.getEnergy();
        mav.getModel().put("data", energy);
        return mav;
    }

    @RequestMapping("/reduce")
    public ModelAndView reduce(
            @RequestParam(required = true, defaultValue = "0") final int v) throws IOException {
        service.reduceEnergy(v);
        final ModelAndView mav = new ModelAndView("energy");
        final int energy = service.getEnergy();
        mav.getModel().put("data", energy);
        return mav;
    }
}
