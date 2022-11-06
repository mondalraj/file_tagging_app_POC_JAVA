import java.util.*;

public class Poc {
    static HashMap<String, ArrayList<String>> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        int choice = 1;

        while (choice != 8) {
            System.out.println("Please choose an option:");
            System.out.println("1. Add a new tag to a file");
            System.out.println("2. Remove a tag from a file");
            System.out.println("3. List all files with a given tag");
            System.out.println("4. List of all tags");
            System.out.println("5. List all tags for a given file");
            System.out.println("6. Updating a tag name");
            System.out.println("7. Updating a file name");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            String fileName = new String();
            String tag = new String();
            switch (choice) {
                case 1:
                    System.out.print("Enter the file name: ");
                    fileName = sc.next();
                    System.out.print("Enter the tag: ");
                    tag = sc.next();

                    add_tag(fileName, tag);
                    break;
                case 2:
                    System.out.print("Enter the file name: ");
                    fileName = sc.next();
                    System.out.print("Enter the tag: ");
                    tag = sc.next();

                    remove_tag(fileName, tag);
                    break;
                case 3:
                    System.out.print("Enter the tag: ");
                    tag = sc.next();

                    get_all_files(tag);
                    break;
                case 4:
                    get_all_tags();
                    System.out.print("Get all Tags Functionality not implemented");
                    break;
                case 5:
                    System.out.print("Enter the file name: ");
                    fileName = sc.next();

                    get_all_tags_for_file(fileName);
                    break;
                case 6:
                    System.out.print("Enter the old tag name: ");
                    String oldTag = sc.next();
                    System.out.print("Enter the new tag name: ");
                    String newTag = sc.next();

                    update_tag(oldTag, newTag);
                    break;
                case 7:
                    System.out.print("Enter the old file name: ");
                    String oldFileName = sc.next();
                    System.out.print("Enter the new file name: ");
                    String newFileName = sc.next();

                    update_file(oldFileName, newFileName);
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Invalid choice");

                    Set<String> keys = map.keySet();
                    for (String key : keys) {
                        System.out.println(key + " " + map.get(key));
                    }
            }

        }
    }

    public static void add_tag(String fileName, String tag) {
        if (map.containsKey(tag)) {
            ArrayList<String> list = map.get(tag);
            list.add(fileName.trim());
            map.put(tag, list);
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(fileName.trim());
            map.put(tag, list);
        }
        System.out.println("***** Tag added successfully *****");
    }

    public static void remove_tag(String fileName, String tag) {
        if (map.containsKey(tag)) {
            ArrayList<String> list = map.get(tag);
            int pos = list.indexOf(fileName.trim());
            if (pos != -1) {
                list.remove(pos);
                map.put(tag, list);
                System.out.println("***** Tag removed successfully *****");
            } else {
                System.out.println("***** File not found *****");
            }
        } else {
            System.out.println("***** Tag not found *****");
        }
    }

    public static void get_all_files(String tag) {
        if (map.containsKey(tag)) {
            ArrayList<String> list = map.get(tag);
            System.out.println("***** Files with tag " + tag + " *****");
            for (String file : list) {
                System.out.println(file);
            }
        } else {
            System.out.println("***** Tag not found *****");
        }
    }

    public static void get_all_tags() {
        System.out.println("***** All tags *****");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
    }

    public static void get_all_tags_for_file(String fileName) {
        Set<String> keys = map.keySet();
        for (String key : keys) {
            ArrayList<String> list = map.get(key);
            if (list.contains(fileName.trim())) {
                System.out.println(key);
            }
        }
    }

    public static void update_tag(String oldTag, String newTag) {
        if (map.containsKey(oldTag)) {
            ArrayList<String> list = map.get(oldTag);
            map.remove(oldTag);
            map.put(newTag, list);
            System.out.println("***** Tag updated successfully *****");
        } else {
            System.out.println("***** Tag not found *****");
        }
    }

    public static void update_file(String oldFileName, String newFileName) {
        Set<String> keys = map.keySet();
        for (String key : keys) {
            ArrayList<String> list = map.get(key);
            int pos = list.indexOf(oldFileName.trim());
            if (pos != -1) {
                list.remove(pos);
                list.add(newFileName.trim());
                map.put(key, list);
                System.out.println("***** File updated successfully *****");
            } else {
                System.out.println("***** File not found *****");
            }
        }
    }
}
