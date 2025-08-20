package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.edu.model.entity.History;
import ra.edu.service.HistoryService;

@Controller
@RequestMapping("/histories")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping
    public String showList(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size,
            Model model) {
        Page<History> histories = historyService.findAll(page, size);
        model.addAttribute("histories", histories);
        return "history/history";
    }

}
