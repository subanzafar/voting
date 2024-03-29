package com.octans.smartelec.DataServices;

import com.octans.smartelec.ApiResponse;
import com.octans.smartelec.Models.User;
import com.octans.smartelec.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {

    @Autowired
    private UserRepository userRepository;

    public ApiResponse allUsers(String domain) {
        ApiResponse response = new ApiResponse();
        try {
            response.setStatusCode(200);
            response.setMessage("User Found!");
            response.setData(userRepository.findByElectionIdAndEmailDomain(0, domain));
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured!");
            return response;
        }
    }

    public ApiResponse allUsersOfElec(int elecId) {
        ApiResponse response = new ApiResponse();
        try {
            response.setStatusCode(200);
            response.setMessage("Users Found!");
            response.setData(userRepository.findAllByElectionId(elecId));
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured!");
            return response;
        }
    }

    public ApiResponse signUp(User user) {
        ApiResponse response = new ApiResponse();
        try {
            userRepository.save(user);
            response.setStatusCode(200);
            response.setMessage("User Created!");
            response.setData(user);

            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ApiResponse deleteUser(String userId) {
        ApiResponse response = new ApiResponse();
        try {
            userRepository.deleteByUserId(userId);
            response.setStatusCode(200);
            response.setMessage("User Created!");
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ApiResponse updateUser(String userId, boolean available) {
        ApiResponse response = new ApiResponse();
        try {
            userRepository.findByUserId(userId).map(u -> {
                u.setAvailable(available);
                return userRepository.save(u);
            });
            response.setStatusCode(200);
            response.setMessage("User Created!");
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    // public ApiResponse signIn(String email, String password) {
    // ApiResponse response = new ApiResponse();

    // try {
    // User existingUser = userRepository.findByEmailAndPassword(email, password);
    // existingUser.setEnrolledElection(null);
    // // existingUser.setMyElections(null);
    // response.setStatusCode(200);
    // response.setMessage("Signed in User!");
    // response.setData(existingUser);
    // return response;
    // } catch (Exception e) {
    // response.setStatusCode(500);
    // response.setMessage("User doesn\'t exists!");
    // return response;
    // }

    // }

    // public ApiResponse userExists(String email) {
    // ApiResponse response = new ApiResponse();

    // try {
    // User existingUser = userRepository.findByEmail(email);
    // response.setStatusCode(200);
    // response.setMessage("No User!");
    // response.setData(existingUser == null);
    // return response;
    // } catch (Exception e) {
    // response.setStatusCode(500);
    // response.setMessage("User doesn\'t exists!");
    // return response;
    // }
    // }

    // public ApiResponse forgotPassword(String email) {
    // ApiResponse response = new ApiResponse();

    // try {
    // int randomPin = (int) (Math.random() * 900000) + 100000;
    // String otp = String.valueOf(randomPin);
    // User existingUser = userRepository.findByEmail(email);
    // if (existingUser == null) {
    // throw new IllegalStateException("User with this email doesn\'t exist.");
    // }
    // SimpleMailMessage mail = new SimpleMailMessage();
    // mail.setTo(email);
    // mail.setSubject("Email Verification");
    // mail.setText(otp
    // + " is the OTP for the verification.\nOTPs are secret. Therefore, do not
    // disclose this to anyone.");
    // javaMailSender.send(mail);
    // response.setStatusCode(200);
    // response.setMessage("Code Sent!");
    // response.setData(otp);
    // return response;
    // } catch (MailException e) {
    // response.setStatusCode(500);
    // response.setMessage(e.getMessage());
    // return response;
    // }
    // }

    // public ApiResponse sendEmail(String email) {
    // ApiResponse response = new ApiResponse();
    // int randomPin = (int) (Math.random() * 900000) + 100000;
    // String otp = String.valueOf(randomPin);
    // SimpleMailMessage mail = new SimpleMailMessage();
    // mail.setTo(email);
    // mail.setSubject("Email Verification");
    // mail.setText(
    // otp + " is the OTP for the verification.\nOTPs are secret. Therefore, do not
    // disclose this to anyone.");
    // try {
    // javaMailSender.send(mail);
    // } catch (MailException e) {
    // response.setStatusCode(500);
    // response.setMessage(e.getLocalizedMessage());
    // return response;
    // }
    // response.setStatusCode(200);
    // response.setMessage("Code Sent!");
    // response.setData(otp);
    // return response;
    // }

    // public ApiResponse updatePassword(String email, String newPass) {
    // ApiResponse response = new ApiResponse();
    // try {
    // User existingUser = userRepository.findByEmail(email);
    // System.out.println(existingUser == null);
    // if (existingUser != null) {
    // // existingUser.setEmail(email);
    // // existingUser.setPassword(newPass);
    // // existingUser.setName(existingUser.getName());
    // // existingUser.setMobile(existingUser.getMobile());
    // // User updatedUser = userRepository.save(existingUser);
    // userRepository.save(existingUser);
    // response.setStatusCode(200);
    // response.setMessage("Password updated!");
    // response.setData(existingUser);
    // return response;
    // }
    // throw new IllegalStateException("Error Occured!");
    // } catch (Exception e) {
    // response.setStatusCode(500);
    // response.setMessage(e.getMessage());
    // return response;
    // }

    // }
}
