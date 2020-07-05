package models.request;

public class BookRequest {

        private Source source;
        private Destination destination;

        /**
         * No args constructor for use in serialization
         *  @param src
         * @param des
         */

        /**
         *
         * @param destination
         * @param source
         */
        public BookRequest(Source source, Destination destination) {
            super();
            this.source = source;
            this.destination = destination;
        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        public Destination getDestination() {
            return destination;
        }

        public void setDestination(Destination destination) {
            this.destination = destination;
        }


    }
