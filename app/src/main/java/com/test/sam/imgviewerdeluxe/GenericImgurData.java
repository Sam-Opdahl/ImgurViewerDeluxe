package com.test.sam.imgviewerdeluxe;

import java.util.ArrayList;
import java.util.List;

public class GenericImgurData {
    private List<JsonData> data = new ArrayList<>();

    public List<JsonData> getData() {
        return data;
    }

    public class JsonData {
        private String title;
        private String link;

        public String getTitle() {
            return title;
        }

        public String getLink() {
            return link;
        }
    }
}
