package com.example.apartmentManagement.controller;

import antlr.StringUtils;
import com.example.apartmentManagement.modal.Maintenance;
import com.example.apartmentManagement.modal.Message;
import com.example.apartmentManagement.modal.Room;
import com.example.apartmentManagement.modal.User;
import com.example.apartmentManagement.service.MaintenanceService;
import com.example.apartmentManagement.service.MessageService;
import com.example.apartmentManagement.service.RoomService;
import com.example.apartmentManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
public class ResidentController {
    @Autowired
    UserService userService;
    @Autowired
    MaintenanceService maintenanceService;
    @Autowired
    MessageService messageService;

    @RequestMapping("/resident-info")
    public String showResidentInfo(HttpSession session, HttpServletRequest request) {
        Integer id = (Integer)session.getAttribute("id");
        User user = userService.editUser(id);
        request.setAttribute("mode", "RESIDENT-INFO");
        request.setAttribute("user",user);
        return "residentLog";
    }

    @RequestMapping("/edit-resident-info")
    public String editResidentInfo(@RequestParam Integer id, String name, String email, String password, HttpServletRequest request) {
        User user = userService.editUser(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userService.saveMyUser(user);
//        request.setAttribute("mode", "RESIDENT-INFO");
        request.setAttribute("updateRisidentInfo", "User information updated");
        return "residentLog";
    }

    @RequestMapping("/resident-maintenance")
    public String showMaintenanceForm(HttpServletRequest request) {
        request.setAttribute("mode", "RESIDENT-MAINTENANCE");
        return "residentLog";
    }

    @RequestMapping("/maintenance-submit")
    public String maintenanceSubmit(@RequestParam String name, String room, String description, HttpServletRequest request) {
        Maintenance maintenance = new Maintenance(name,room,description);
        maintenanceService.saveMaintenance(maintenance);
        request.setAttribute("saveMaintenanceInfo", "New MaintenanceSubmitted");
        return "residentLog";
    }

    @RequestMapping("/resident-new-message")
    public String residentMessage(HttpServletRequest request) {
        request.setAttribute("mode", "RESIDENT-MESSAGE");
        return "residentLog";
    }

    @RequestMapping("/resident-history-message")
    public String showHistoryMessage(HttpSession session, HttpServletRequest request) {
        Integer id = (Integer)session.getAttribute("id");
        User user = userService.editUser(id);
        List<Message> messages = user.getMessages();
        request.setAttribute("mode", "RESIDENT-HISTORY-MESSAGE");
        request.setAttribute("messages" ,messages);
        return "residentLog";
    }

    @RequestMapping("/resident-message-submit")
    public String residentMessageSubmit(HttpSession session, @RequestParam String name, String subject, String detail, HttpServletRequest request) {
        Integer id = (Integer)session.getAttribute("id");
        User user = userService.editUser(id);
        List<String> details = new ArrayList<>();
        details.add(detail);
        Message message = new Message(name,subject,details, user);
        messageService.saveMessage(message);
        request.setAttribute("saveMessageInfo", "Message submitted");
        return "residentLog";
    }

    @RequestMapping("/resident-choose-message-detail")
    public String residentChooseMessageDetail(@RequestParam String id, HttpServletRequest request) {
        Message message = messageService.findById(Integer.parseInt(id));
        List<String> details = message.getDetails();
        request.setAttribute("message", message);
        request.setAttribute("details", details);
//        request.setAttribute("mode", "RESIDENT-HISTORY-MESSAGE");
        request.setAttribute("pick", "RESIDENT-CHOOSE-MESSAGE-DETAIL");
        return "residentLog";
    }

    @RequestMapping("/resident-choose-message-submit")
    public String residentChooseMessageSubmit(@RequestParam String id, String newDetail, HttpSession session, HttpServletRequest request) {
        Message message = messageService.findById(Integer.parseInt(id));
        message.getDetails().add(newDetail);
        messageService.saveMessage(message);
        request.setAttribute("residentReplyInfo", "Message replied");
        request.setAttribute("name", session.getAttribute("name"));
        return "residentLog";
    }

    @RequestMapping("/resident-delete-message")
    public String residentDeleteMessage(@RequestParam String deleteId, HttpSession session, HttpServletRequest request) {
        messageService.deleteById(Integer.parseInt(deleteId));
        Integer id = (Integer)session.getAttribute("id");
        User user = userService.editUser(id);
        List<Message> messages = user.getMessages();
        request.setAttribute("mode", "RESIDENT-HISTORY-MESSAGE");
        request.setAttribute("messages" ,messages);
        return "residentLog";
    }
}
