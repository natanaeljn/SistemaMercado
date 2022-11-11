package Gui.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public class Utils {
	public static <T> void formatTableColumnDate(TableColumn<T, Date> tableColumn, String format) {
	
		tableColumn.setCellFactory(column -> {
			
			TableCell<T, Date> cell = new TableCell<T, Date>() {
				private SimpleDateFormat sdf = new SimpleDateFormat(format);

				@Override
				protected void updateItem(Date item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						setText(sdf.format(item));
					}
				}
			};
			return cell;
		});
		}

}
