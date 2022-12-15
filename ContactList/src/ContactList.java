import java.util.*;

public class ContactList {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Map<String, Person> contacts = new HashMap<>();
        boolean more = true;

        while(more) {
            System.out.println("Choose an option: READ, ADD, DELETE, or UPDATE");
            String answer = scan.nextLine();

            if (answer.equalsIgnoreCase("add")) {
                Person newPerson = addPerson();
                contacts.put(newPerson.getName(),newPerson);
            } else if (answer.equalsIgnoreCase("delete")) {
                deletePerson(contacts);
            } else if (answer.equalsIgnoreCase("read")){
                readContacts(contacts);
            } else if (answer.equalsIgnoreCase("update")){
                updatePerson(contacts);
            }
            System.out.println("Is there more you would like to do? [true or false]?");
            more = scan.nextBoolean();
            scan.nextLine();
        }
        System.out.println("All done. Goodbye.");
    }

    public static Person addPerson(){
        System.out.println("name: ");
        String name = scan.nextLine();
        if (name == null || !Character.isLetter(name.charAt(0))){
            badName(name);
        }

        System.out.println("phone number: ");
        String phoneNumber = scan.nextLine();
        if (phoneNumber.length() != 7){
            badPhoneNumber(phoneNumber);
        }

        System.out.println("email: ");
        String email = scan.nextLine();
        if (!email.contains("@") || !email.contains(".com")){
            badEmail(email);
        }
        return new Person(name, phoneNumber, email);
    }

    public static void badPhoneNumber(String phoneNumber) {
        while (phoneNumber.length() != 7){
            System.out.println("Invalid phone number. Please try again and input 7 digits." + "\nphone number: ");
            phoneNumber = scan.nextLine();
        }
    }

    public static void badName(String name){
        while (name == null || !Character.isLetter(name.charAt(0))){
            System.out.println("Invalid name. Do not start a name with a number or special character. Please try again." + "\nname: ");
            name = scan.nextLine();
        }
    }

    public static void badEmail(String email){
        while (!email.contains("@") || !email.contains(".com")){
            System.out.println("Invalid email. Please include '@' and '.com'. Please try again." + "\nemail: ");
            email = scan.nextLine();
        }
    }

    public static void deletePerson(Map<String, Person> contacts){
        if (contacts.isEmpty()){
            System.out.println("There are 0 contacts saved. Create a contact first.");
        } else {
            System.out.println("Who do you want to delete?");
            contacts.remove(scan.nextLine());
        }
    }

    public static void readContacts (Map<String, Person> contacts) {
        if (contacts.isEmpty()){
            System.out.println("There are 0 contacts saved. Create a contact first.");
        } else {
            for (Object key: contacts.keySet()){
                System.out.println(key + " = " + contacts.get(key));
            }
        }
    }

    public static void updatePerson (Map<String, Person> contacts) {
        if (contacts.isEmpty()){
            System.out.println("There are 0 contacts saved. Create a contact first.");
        } else {
            System.out.println("Who do you want to update?");
            String name = scan.nextLine();
            Person person = contacts.get(name);

            System.out.println("What do you want to change (name, phone, or email)?");
            String answer = scan.nextLine();
            //update name
            if (answer.equalsIgnoreCase("name")) {
                System.out.println("Updated name: ");
                person.setName(scan.nextLine());
                contacts.put(person.getName(), person);
                contacts.remove(name);
            }
            //update phone number
            else if (answer.equalsIgnoreCase("phone")) {
                System.out.println("Updated phone number: ");
                person.setPhoneNumber(scan.nextLine());
            }
            //update email
            else if (answer.equalsIgnoreCase("email")) {
                System.out.println("Updated email: ");
                person.setEmail(scan.nextLine());
            }
        }
    }
}