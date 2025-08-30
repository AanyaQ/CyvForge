package net.cyvforge.command;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientColorHelper;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.event.CommandInitializer;
import net.cyvforge.util.defaults.CyvCommand;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

/**Custom client-sided commands*/
public class CommandHelp extends CyvCommand {
     public CommandHelp() {
         super("help");
         this.usage = "[subcommand]";
         this.helpString = "Get the subcommand help menu for the " + this.parent + " command.";
     }

     @Override
     public void run(ICommandSender sender, String[] args) {
         String commandPath;
         String commandName;
         ArrayList<CyvCommand> subCommands;

         if (this.parent == null) { //executed from the very base command
             commandPath = "/cyv help";
             commandName = "cyv";
             subCommands = CommandInitializer.cyvCommands;
         } else { //executed from within a cyvclient subcommand
             commandPath = "/cyv " + this.parent.name + " help";
             commandName = this.parent.name;
             subCommands = this.parent.subCommands;
         }

         if (args.length == 0 && !subCommands.isEmpty()) { //no arguments and haven't reached top-level command
             List<String> helpText = new ArrayList<String>();
             helpText.add(CyvClientColorHelper.color1.getChatFormatting() + commandName +  " help menu:\247r");

             String chatColor2 = CyvClientConfig.getBoolean("whiteChat", false) ?
                     CyvClientColorHelper.colors.get(12).getChatFormatting()
                     : CyvClientColorHelper.color2.getChatFormatting();

             for (CyvCommand c : subCommands) {
                 helpText.add(CyvClientColorHelper.color1.getChatFormatting() + c.name + ": "
                         + chatColor2 + c.helpString);
             }

             helpText.add(CyvClientColorHelper.color1.getChatFormatting()
                     + "\247oNote: Use " + commandPath + " [command] for details");

             CyvForge.sendChatMessage(String.join("\n", helpText));

         } else { //arguments, or reached top-level command in command tree
             CyvCommand targetCommand = null; //target command

             if (args.length > 0) { //details for a specific command
                 Outer: for (CyvCommand cmd : subCommands) { //loop through subcommands
                     if (!cmd.name.toLowerCase().equals(args[0])) {
                         for (String s : cmd.aliases) {
                             if (s.toLowerCase().equals(args[0])) {
                                 targetCommand = cmd;
                                 break Outer;
                             }
                         }
                     } else {
                         targetCommand = cmd;
                         break Outer;
                     }
                 }

             } else { //show info about self
                 targetCommand = parent;
             }

             if (targetCommand == null) {
                 CyvForge.sendChatMessage("Command not found. Use " + commandPath + " for a list of commands.");
                 return;

             } else {
                 List<String> commandNames = new ArrayList<String>();
                 commandNames.add(targetCommand.name);
                 if (targetCommand.aliases != null) commandNames.addAll(targetCommand.aliases);

                 CyvForge.sendChatMessage("Command: /" + args[0] + "\n"
                         + "Aliases: " + String.join(", ", commandNames) + "\n"
                         + "Usage: " + targetCommand.usage + "\n"
                         + targetCommand.getDetailedHelp()
                         + "\n" + CyvClientColorHelper.color1.getChatFormatting() +
                         "\247oNote: Use " + commandPath + " to list subcommands."
                 );

                 return;
             }

         }
     }



}
