package ra.edu.ex08_09.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.edu.ex08_09.model.Transaction;
import ra.edu.ex08_09.service.TransactionService;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("transactions")
@RequestMapping("/transactionController")
public class TransactionController {

    @ModelAttribute("transactions")
    public List<Transaction> initTransactions() {
        return new ArrayList<>();
    }

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public String showTransaction(@ModelAttribute("transactions") List<Transaction> transactionList,
                                  Model model) {
        System.out.println("Lỗi ở controller list");
        model.addAttribute("transactions", transactionList);
        return "ex08_09/list";
    }

    @GetMapping("/add")
    public String initAddTransaction(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "ex08_09/add";
    }

    @PostMapping("/add")
    public String addTransaction(@Valid @ModelAttribute("transaction") Transaction transaction,
                                 BindingResult result,
                                 @ModelAttribute("transactions") List<Transaction> transactionList) {
        if (result.hasErrors()) {
            return "ex08_09/add";
        }
        transactionService.addTransaction(transactionList, transaction);
        return "redirect:/transactionController";
    }

    @GetMapping("/editPage/{id}")
    public String initEditTransaction(@PathVariable("id") int id,
                                      @ModelAttribute("transactions") List<Transaction> transactionList,
                                      Model model) {
        Transaction transactionEdit = transactionService.findTransactionById(id, transactionList);
        if (transactionEdit == null) {
            return "ex08_09/list";
        } else {
            model.addAttribute("transaction", transactionEdit);
            return "ex08_09/update";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateTransaction(@PathVariable("id") int id,
                                    @ModelAttribute("transactions") List<Transaction> transactionList,
                                    @Valid @ModelAttribute("transaction") Transaction transactionUpdate,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return "ex08_09/add";
        }
        transactionService.editTransaction(id, transactionList, transactionUpdate);
        return "redirect:/transactionController";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable("id") int id,
                                    @ModelAttribute("transactions") List<Transaction> transactionList) {
        transactionService.deleteTransaction(id, transactionList);
        return "redirect:/transactionController";
    }
}
