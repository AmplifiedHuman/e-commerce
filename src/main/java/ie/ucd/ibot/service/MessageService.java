package ie.ucd.ibot.service;

import ie.ucd.ibot.entity.*;
import ie.ucd.ibot.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    @Transactional
    public void createMessage(User user, String messageContent, String subject, MessageType type){
        Message message = new Message();
        if (user.getMessages() == null) {
            user.setMessages(new ArrayList<>());
        }
        message.setType(type);
        message.setUser(user);
        message.setMessageContent(messageContent);
        message.setSubject(subject);
        user.getMessages().add(message);
        userRepository.save(user);
    }

    @Transactional
    public void updateMessage(Long id, String messageContent, String Subject, MessageType messageType) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            Message newMessage = message.get();
            newMessage.setSubject(Subject);
            newMessage.setMessageContent(messageContent);
            newMessage.setType(messageType);
            messageRepository.save(newMessage);
        }
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

}
