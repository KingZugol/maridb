package com.werkstuck.demo.Controller;

import com.werkstuck.demo.Model.WeedByEffect;
import com.werkstuck.demo.Model.WeedByFlavor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class MyAppController {
RestTemplate restTemplate = new RestTemplate();

@GetMapping("/effects")
    public String getEffects(Model model){
     String url = "http://strainapi.evanbusse.com/7wvDuw5/searchdata/effects";
    WeedByEffect[] weedByEffect = restTemplate.getForObject(url, WeedByEffect[].class);
    model.addAttribute("weedByEffect", weedByEffect);
    return "fragments/WeedByEffects";
}
@GetMapping("/flavors")
    public String getFlavours(Model model){
    String url = "http://strainapi.evanbusse.com/7wvDuw5/searchdata/flavors";
    String[] flavors = restTemplate.getForObject(url, String[].class);
    model.addAttribute("flavors", flavors);
    return "fragments/Flavors";

}

@GetMapping("/flavors/{flavor}")
    public String getWeedByFlavor(Model model, @PathVariable("flavor") String flavor) throws UnsupportedEncodingException {
    flavor.toLowerCase();
    String url ="http://strainapi.evanbusse.com/7wvDuw5/strains/search/flavor/" + URLEncoder.encode(flavor, StandardCharsets.UTF_8.toString());
    WeedByFlavor[] weedByFlavors = restTemplate.getForObject(url, WeedByFlavor[].class);
    model.addAttribute("WeedByFlavors", weedByFlavors);
    return "fragments/WeedByFlavor";

}

}
