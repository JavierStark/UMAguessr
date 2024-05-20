package org.umaguessr.backend;

import java.util.List;

public interface Database {
	
	List<Image> read(String paths);
	
}
