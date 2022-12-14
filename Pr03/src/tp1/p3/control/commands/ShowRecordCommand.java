package tp1.p3.control.commands;

import tp1.p3.control.Command;
import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class ShowRecordCommand extends Command{

	protected String getName() {
		return Messages.COMMAND_SHOW_RECORD_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_SHOW_RECORD_SHORTCUT;
	}

	public String getDetails() {
		return Messages.COMMAND_SHOW_RECORD_DETAILS;
	}

	public String getHelp() {
		return Messages.COMMAND_SHOW_RECORD_HELP;
	}

	public boolean execute(GameWorld game) {
		// TODO Auto-generated method stub
		return false;
	}

}
