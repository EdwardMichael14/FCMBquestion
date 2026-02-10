public class Algorithm2 {
    public static void main(String[] args) {

            String word = "this is a boy";
            System.out.print(capitalize(word));
        }

        public static String capitalize(String text){

            String[] array = text.split(" ");
            StringBuilder capitalized = new StringBuilder();
            for (int i = 0; i < array.length; i++) {
                String word = array[i];

                word = Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
                capitalized.append(word);

            }
            return capitalized.toString();
        }
    }
