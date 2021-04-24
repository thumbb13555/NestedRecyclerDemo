package com.noahliu.nestedrecyclerdemo;

import java.util.List;

class MyData {
    private String title;
    private List<NestedData> nesData;

    public MyData(String title, List<NestedData> nesData) {
        this.title = title;
        this.nesData = nesData;
    }

    public String getTitle() {
        return title;
    }

    public List<NestedData> getNesData() {
        return nesData;
    }

    static class NestedData{
        private String nesTitle;

        public NestedData(String title) {
            this.nesTitle = title;
        }

        public String getNesTitle() {
            return nesTitle;
        }
    }
}
