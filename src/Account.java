public class Account
{
        private String name;

        public Account(String name) //constructor method that custom initialize the instance variables to said values
        {
                this.name = name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getName()
        {
                return name;
        }
}
