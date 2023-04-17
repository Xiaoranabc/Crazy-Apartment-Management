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
import java.util.List;

@Controller
@RequestMapping()
public class StaffLogController {
    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;

    @Autowired
    MaintenanceService maintenanceService;

    @Autowired
    MessageService messageService;

    @RequestMapping("/user-management")
    public String showUserManagement(HttpServletRequest request) {
        request.setAttribute("mode","MODE-USER-MANAGEMENT");
        return "staffLog";
    }

    @RequestMapping("/message")
    public String showMessage(@RequestParam(name="mode", defaultValue = "MODE-MESSAGE") String mode, HttpServletRequest request) {
        request.setAttribute("mode", mode);
        return "staffLog";
    }


    @GetMapping("/show-user")
    public String showAllUsers(@RequestParam(name="pick", defaultValue = "SHOW-USERS")String pick, HttpServletRequest request) {
        request.setAttribute("users", userService.showAllUsers());
        request.setAttribute("mode", "MODE-USER-MANAGEMENT");
        request.setAttribute("pick", pick);
        return "staffLog";
    }

    @GetMapping("/show-roles")
    public String showRoleUsers(@RequestParam String role, HttpServletRequest request) {
        request.setAttribute("users", userService.showRoleUsers(role));
        request.setAttribute("mode", "MODE-USER-MANAGEMENT");
        request.setAttribute("pick", "SHOW-USERS");
        return "staffLog";
    }

    @RequestMapping("/search-user")
    public String showSearchUser(HttpServletRequest request) {
        request.setAttribute("mode", "MODE-USER-MANAGEMENT");
        request.setAttribute("pick","SEARCH-USERS");
        return "staffLog";
    }

    @GetMapping("/search-user-result")
    public String showSearchUserResult(@RequestParam String name, @RequestParam String id, HttpServletRequest request) {
        List<User> nameUser = userService.showNameUser(name);
        List<User> idUser = userService.showIdUser( (id == null || id == "") ? -1 : Integer.parseInt(id));
        if(name!="" && id== "" && nameUser.size()!=0) {
            request.setAttribute("users", nameUser);
            request.setAttribute("mode", "MODE-USER-MANAGEMENT");
            request.setAttribute("pick", "SEARCH-USERS");
            request.setAttribute("search","RESULT");
        } else if(id!="" && idUser.size()!=0) {
            request.setAttribute("users",idUser);
            request.setAttribute("mode", "MODE-USER-MANAGEMENT");
            request.setAttribute("pick", "SEARCH-USERS");
            request.setAttribute("search","RESULT");
        } else{
            request.setAttribute("mode", "MODE-USER-MANAGEMENT");
            request.setAttribute("pick", "SEARCH-USERS");
            request.setAttribute("search", "NULL-ERROR");
        }
        return "staffLog";
    }

    @RequestMapping("/delete-user")
    public String deleteUser(@RequestParam int id, HttpServletRequest request) {
        userService.deleteMyUser(id);
        request.setAttribute("users", userService.showAllUsers());
        request.setAttribute("mode", "ALL_USERS");
        request.setAttribute("pick", "SHOW-USERS");
        return "staffLog";
    }

    @RequestMapping("/edit-user")
    public String editUser(@RequestParam int id, HttpServletRequest request) {
        User user = userService.editUser(id);
        request.setAttribute("user", user);
        request.setAttribute("mode", "MODE-USER-MANAGEMENT");
        request.setAttribute("pick", "SEARCH-USERS");
        request.setAttribute("search","SEARCH-EDIT");
        return "staffLog";
    }

    @RequestMapping("/edit-user-result")
    public String editUserResult(@RequestParam String changeName, @RequestParam String changeEmail,@RequestParam int id, HttpServletRequest request) {
        User user = userService.editUser(id);
        if(user != null) {
            if(changeName=="" && changeEmail!="") {
                user.setEmail(changeEmail);
            } else if(changeName!="" && changeEmail=="") {
                user.setName(changeName);
            } else {
                user.setEmail(changeEmail);
                user.setName(changeName);
            }
            userService.saveMyUser(user);
        }
        request.setAttribute("user", userService.editUser(id));
        request.setAttribute("mode", "MODE-USER-MANAGEMENT");
        request.setAttribute("pick", "SEARCH-USERS");
        request.setAttribute("saveInfo", "User information saved");
        return "staffLog";
    }

    @RequestMapping("/room-management")
    public String showRoomManagement(HttpServletRequest request) {
        request.setAttribute("mode", "MODE-ROOM-MANAGEMENT");
        return "staffLog";
    }

    @RequestMapping("/show-room")
    public String showAllRoom(HttpServletRequest request) {

        request.setAttribute("rooms",roomService.showAllRooms() );
        request.setAttribute("mode", "MODE-ROOM-MANAGEMENT");
        request.setAttribute("pick","SHOW-ROOM");
        return "staffLog";
    }

    @RequestMapping("/edit-room")
    public String showEditRoom(HttpServletRequest request) {
        request.setAttribute("mode", "MODE-ROOM-MANAGEMENT");
        request.setAttribute("pick", "EDIT-ROOM");
        return "staffLog";
    }

    @RequestMapping("/search-room-result")
    public String showSearchResult(@RequestParam String number, HttpServletRequest request) {
        Room room = roomService.findByNumber(Integer.parseInt(number));
        List<User> usersInRoom = room.getUsersInRoom();
        request.setAttribute("room", room);
        request.setAttribute("mode", "MODE-ROOM-MANAGEMENT");
        request.setAttribute("search","ROOM-RESULT");
        request.setAttribute("usersInRoom", usersInRoom);
        return "staffLog";
    }

    @RequestMapping("/edit-room-submit")
    public String editRoomSubmit(@RequestParam Integer roomNumber, @RequestParam Integer user_id, @RequestParam String parking, HttpServletRequest request) {
        Room room = roomService.findByNumber(roomNumber);
        User user = userService.editUser(user_id);
        user.setRoom(room);
        if(room!=null) {
            if(user_id!=null && parking==null) {
                room.setUsersInRoom(user);
            } else if(user_id==null && parking!=null) {
                room.setParking(parking);
            } else {
                room.setUsersInRoom(user);
                room.setParking(parking);
            }
            roomService.saveRoom(room);
        }
        request.setAttribute("room", room);
        request.setAttribute("mode", "MODE-ROOM-MANAGEMENT");
        request.setAttribute("pick", "EDIT-ROOM");
        request.setAttribute("saveRoomInfo", "Room information saved");
        return "staffLog";
    }

    @RequestMapping("/staff-message")
    public String showMessagePage(HttpServletRequest request) {
        request.setAttribute("mode","MODE-MESSAGE");
        return "staffLog";
    }

    @RequestMapping("/message-maintenance")
    public String showAllMaintenance(HttpServletRequest request) {
        request.setAttribute("maintenances",maintenanceService.showAllMaintenance());
        request.setAttribute("mode","MODE-MESSAGE");
        request.setAttribute("pick", "MESSAGE-MAINTENANCE");
        return "staffLog";
    }

    @RequestMapping("/delete-maintenance")
    public String deleteMaintenance(@RequestParam String id, HttpServletRequest request) {
        maintenanceService.deleteById(Integer.parseInt(id));
        request.setAttribute("maintenances",maintenanceService.showAllMaintenance());
        request.setAttribute("mode","MODE-MESSAGE");
        request.setAttribute("pick", "MESSAGE-MAINTENANCE");
        return "staffLog";
    }

    @RequestMapping("/message-other")
    public String showMessageOther(HttpServletRequest request) {
        request.setAttribute("messages", messageService.showAllMessage());
        request.setAttribute("mode","MODE-MESSAGE");
        request.setAttribute("pick", "MESSAGE-OTHER");
        return "staffLog";
    }

    @RequestMapping("/delete-message")
    public String deleteMessage(@RequestParam String id,  HttpServletRequest request) {
        messageService.deleteById(Integer.parseInt(id));
        request.setAttribute("messages", messageService.showAllMessage());
        request.setAttribute("mode","MODE-MESSAGE");
        request.setAttribute("pick", "MESSAGE-OTHER");
        return "staffLog";
    }

    @RequestMapping("/show-message-detail")
    public String showMessageDetail(@RequestParam String MessageId, HttpServletRequest request) {
        Message message = messageService.findById(Integer.parseInt(MessageId));
        List<String> details = message.getDetails();
        request.setAttribute("pick", "MESSAGE-DETAIL");
        request.setAttribute("details", details);
        request.setAttribute("message", message);
        request.setAttribute("mode","MODE-MESSAGE");
        return "staffLog";
    }

    @RequestMapping("/message-detail-submit")
    public String submitMessage(@RequestParam String id, String newDetail, HttpSession session, HttpServletRequest request) {
        Message message = messageService.findById(Integer.parseInt(id));
        message.getDetails().add(newDetail);
        messageService.saveMessage(message);
        request.setAttribute("mode","MODE-MESSAGE");
        request.setAttribute("name",session.getAttribute("name"));
        request.setAttribute("saveMessageInfo", "Message replied");
        return "staffLog";
    }
}
