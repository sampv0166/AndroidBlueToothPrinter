package com.demo.ess;
    public class Data {
        private int id;
        private String PhoneNumber;
        private    String CustomerName;
        private  String EventDate;
        private  String EventLocation;
        private   String NumberOfPax;
        private String Amount;
        private  String Remarks;

        public Data(){
        }

        public Data(int id, String PhoneNumber,String CustomerName,String EventDate,String EventLocation,String NumberOfPax,
                    String Amount,String Remarks){
            this.id = id;
            this.PhoneNumber = PhoneNumber;
            this.CustomerName = CustomerName;
            this.EventDate = EventDate;
            this.EventLocation = EventLocation;
            this.NumberOfPax = NumberOfPax;
            this.Amount = Amount;
            this.Remarks = Remarks;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.PhoneNumber = phoneNumber;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String customerName) {
            this.CustomerName = customerName;
        }

        public String getEventDate() {
            return EventDate;
        }

        public void setEventDate(String eventDate) {
            this.EventDate = eventDate;
        }

        public String getEventLocation() {
            return EventLocation;
        }

        public void setEventLocation(String eventLocation) {
            this.EventLocation = eventLocation;
        }

        public String getNumberOfPax() {
            return NumberOfPax;
        }

        public void setNumberOfPax(String numberOfPax) {
            this.NumberOfPax = numberOfPax;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String amount) {
            this. Amount = amount;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String remarks) {
            this.Remarks = remarks;
        }
    }


