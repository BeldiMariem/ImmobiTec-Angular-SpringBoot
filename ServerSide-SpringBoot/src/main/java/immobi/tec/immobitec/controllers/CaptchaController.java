package immobi.tec.immobitec.controllers;

import cn.apiclub.captcha.Captcha;
import immobi.tec.immobitec.services.CaptchaGeneratorService;
import immobi.tec.immobitec.services.CaptchaSettings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/app")
@CrossOrigin(origins="http://localhost:4200")
public class CaptchaController {
    @GetMapping("/verify")
    @ResponseBody
    public CaptchaSettings register(Model model) {
        return genCaptcha();
    }

    @PostMapping("/verify")
    @ResponseBody
    public Map<String, Object> verify(@ModelAttribute CaptchaSettings captchaSettings) {
        Map<String, Object> result = new HashMap<>();
        if(captchaSettings.getCaptcha().equals(captchaSettings.getHiddenCaptcha())){
            result.put("success", true);
            result.put("message","Captcha verified successfully");
        } else {
            result.put("success", false);
            result.put("message","Invalid Captcha");
        }
        return result;
    }

    private CaptchaSettings genCaptcha() {
        CaptchaSettings captchaSettings = new CaptchaSettings();
        Captcha captcha = CaptchaGeneratorService.generateCaptcha(260, 80);
        captchaSettings.setHiddenCaptcha(captcha.getAnswer());
        captchaSettings.setCaptcha("");
        captchaSettings.setRealCaptcha(CaptchaGeneratorService.encodeCaptchatoBinary(captcha));
        return captchaSettings;
    }
}


