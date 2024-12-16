package org.example.splitwisedec24.commands;

import org.example.splitwisedec24.exceptions.CommandNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    private Map<String, Command> map;

    private CommandRegistry(){
        map = new HashMap<>();
    }

    private static CommandRegistry INSTANCE = null;

    public static CommandRegistry getInstance(){
        if(INSTANCE == null){
            INSTANCE = new CommandRegistry();
        }
        return INSTANCE;
    }

    public void register(String key, Command command){
        map.put(key, command);
    }

    public Command get(String command) throws CommandNotFoundException {
        String[] commandArr = command.split(" ");
        for(String keyword: commandArr){
            if(map.containsKey(keyword)){
                return map.get(keyword);
            }
        }
        throw new CommandNotFoundException(command + " seems to be invalid");
    }
}
