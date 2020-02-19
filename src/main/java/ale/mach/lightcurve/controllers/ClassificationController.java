package ale.mach.lightcurve.controllers;

import ale.mach.lightcurve.model.TestCurveDTO;
import ale.mach.lightcurve.repository.TestCurveNegativeRepository;
import ale.mach.lightcurve.repository.TestCurvePositiveRepository;
import ale.mach.lightcurve.service.EvaluateData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/classification")
public class ClassificationController {
    private final TestCurvePositiveRepository testCurvePositiveRepository;
    private final TestCurveNegativeRepository testCurveNegativeRepository;
    private final EvaluateData evaluateData;
    public ClassificationController(TestCurvePositiveRepository testCurvePositiveRepository, TestCurveNegativeRepository testCurveNegativeRepository, EvaluateData evaluateData) {
        this.testCurvePositiveRepository = testCurvePositiveRepository;
        this.testCurveNegativeRepository = testCurveNegativeRepository;
        this.evaluateData = evaluateData;
    }

    @GetMapping()
    public String initEvaluation(Model model){
        TestCurveDTO testCurveDTO = new TestCurveDTO();
        model.addAttribute("testCurveDTO",testCurveDTO);
        model.addAttribute("idRange", testCurvePositiveRepository.findRowCount());
        return "/classification";
    }
    @PostMapping()
    public String evaluate(@Valid TestCurveDTO testCurveDTO, BindingResult result, Model model){
        if (result.hasErrors()){
            return "/classification";
        }
        double[] evaluation = evaluateData.evaluate(testCurveDTO);
        model.addAttribute("evaluation", evaluation);
        model.addAttribute("testCurveDTO", testCurveDTO);
        return "/classification";
    }
}

