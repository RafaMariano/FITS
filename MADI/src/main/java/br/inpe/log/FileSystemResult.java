package br.inpe.log;

public enum FileSystemResult {
	CREATE_SUCCESSFUL,DELETE_SUCCESSFUL,MOVE_SUCCESSFUL;
	
	public static FileSystemResult convert(String str) {
        for (FileSystemResult fileResult : FileSystemResult.values()) {
            if (fileResult.toString().equals(str)) {
                return fileResult;
            }
        }
        return null;
    }
}
