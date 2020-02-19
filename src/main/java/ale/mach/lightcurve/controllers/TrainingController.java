package ale.mach.lightcurve.controllers;

import ale.mach.lightcurve.model.Epoch;
import ale.mach.lightcurve.service.NeuralNetwork;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/training")
public class TrainingController {
    private final NeuralNetwork neuralNetwork;

    public TrainingController(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    @GetMapping()
    public String initTraining(Model model) {
        Epoch epoch = new Epoch();
        model.addAttribute("epoch", epoch);
        return "/training";
    }

    @PostMapping()
    public String training(@Valid Epoch epoch, BindingResult result) {
        if (result.hasErrors()) {
            return "/training";
        }
        neuralNetwork.train(epoch);
        return "/training";
    }

    @GetMapping("/progress")
    public int progress() {
        return (neuralNetwork.getCounter() / neuralNetwork.getMaxSamples());
    }
}
