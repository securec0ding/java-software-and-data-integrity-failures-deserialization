package com.hunter2.deserialization;

import com.hunter2.deserialization.model.ApiResponse;
import com.hunter2.deserialization.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLDecoder;
import java.util.Base64;

@RestController
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String serveHome() {
        return "App is running";
    }

    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public ApiResponse handlePost(@RequestBody String request) throws Exception {
        byte[] decoded = null;
        boolean success = true;
        String urlDecoded = "";
        try {
            urlDecoded = URLDecoder.decode(request, "UTF-8");
            urlDecoded = urlDecoded
                    .replace(" ", "+")
                    .replace('-', '+')
                    .replace('_', '/')
                    .replace("=", "");
            LOGGER.debug("Payload: " + request);
            decoded = Base64.getDecoder().decode(urlDecoded);


            if (decoded == null || decoded.length == 0) {
                throw new Exception("Invalid payload length");
            }

            ByteArrayInputStream bis = new ByteArrayInputStream(decoded);
            ObjectInputStream ois = new ObjectInputStream(bis);
            final Session obj = (Session) ois.readObject();

            LOGGER.info("Obj: " + obj.getClass().getCanonicalName());
            LOGGER.info("Deserialized: " + obj.toString());

        } catch (Exception e) {
            LOGGER.error("Deserialization exception: " + request, e);
            success = false;
        }

        return new ApiResponse(success);
    }

    @GetMapping("/session")
    public ApiResponse serializeSession() throws IOException {
        Session session = new Session("123");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(session);
        byte[] yourBytes = bos.toByteArray();
        return new ApiResponse(Base64.getEncoder().encodeToString(yourBytes));
    }

}