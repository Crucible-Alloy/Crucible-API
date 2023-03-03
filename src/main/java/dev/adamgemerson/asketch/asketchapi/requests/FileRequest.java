package dev.adamgemerson.asketch.asketchapi.requests;

import java.io.File;
import java.util.ArrayList;

public class FileRequest {
        private ArrayList<String> filePath;
        private String operatingSystem;

        public FileRequest() {}

        public FileRequest(ArrayList<String> filePath, String operatingSystem) {
            this.filePath = filePath;
            this.operatingSystem = operatingSystem;
        }

        public void setFilePath(ArrayList<String> filePath) {
            this.filePath = filePath;
        }

        public void setOperatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
        }

        public ArrayList<String> getFilePath() {
            return this.filePath;
        }

        public String getOperatingSystem() {
            return this.operatingSystem;
        }
}
