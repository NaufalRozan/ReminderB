/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ti.umy.ac.id.Reminder_B;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ACER
 */
@Controller
public class projectController {
    @RequestMapping("/inputData")
    public String getData(HttpServletRequest data, Model discountprocess){
        
        String InputName = data.getParameter("name");
        String InputPrice = data.getParameter("price");
        String InputQTY = data.getParameter("quantity");
        String InputPayment = data.getParameter("payment");
        String Diskon = "";
        String Warning = "";
        
        
        Double iPrice = Double.valueOf(InputPrice);
        Double iTotal = Double.valueOf(InputQTY);
        Double iPayment = Double.valueOf(InputPayment);
        Double PriceTotal = iPrice * iTotal;
        Double getTotal = null;
        
        
        
         if (PriceTotal < 16000)
        {
            getTotal = PriceTotal - (0 * PriceTotal/100);
            Diskon = "0%";
        }
        else if (PriceTotal >= 16000 && PriceTotal < 25000)
        {
            getTotal = PriceTotal - (10 * PriceTotal/100);
            Diskon = "10%";
        }
        else
        {
            getTotal = PriceTotal - (15 * PriceTotal/100);
            Diskon = "15%";
        }
        
        Double kembalian = iPayment - getTotal;
        Double tDiskon = PriceTotal - getTotal;
       
        
        
        if (getTotal > iPayment)
        {
            Warning = "Maaf uang anda kurang sebesar Rp. " + kembalian;
        }
        
        else if (getTotal < iPayment)
        {
            Warning = "Kembalian anda sebesar Rp. " + kembalian;
        }
        
        else
        {
            Warning = "Uang anda pas. Terima Kasih";
        }
        
        

         
        discountprocess.addAttribute("name", InputName);
        discountprocess.addAttribute("price", InputPrice);
        discountprocess.addAttribute("quantity", InputQTY);
        discountprocess.addAttribute("totald", getTotal);
        discountprocess.addAttribute("payment", InputPayment);
        discountprocess.addAttribute("Diskon", Diskon);
        discountprocess.addAttribute("kembalian", kembalian);
        discountprocess.addAttribute("Warning", Warning);
        discountprocess.addAttribute("tDiskon", tDiskon);
        discountprocess.addAttribute("total", PriceTotal);
        
        
        return "tableView";
    }
}
