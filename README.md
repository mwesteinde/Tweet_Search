**Exercise 9 / CPEN 221 / Fall 2019**

TextSearch and Tweets
=========


### General Instructions

+ You should try to complete the assigned tasks within 75 minutes.
+ Take your time to read the question.

### Submission Instructions

+ Submit your work to the Github classroom repository that was created for your.
+ **Do not alter the directory/folder structure. You should retain the structure as in this repository.**
+ Do not wait until the last minute to push your work to Github. It is a good idea to push your work at intermediate points as well. _I would recommend that you get your Git and Github workflow set up at the start._

### Honour Code

By submitting your work to Github you agree to the following:

+ You did not consult with any other person in completing the activity.
+ You did not aid any other person in the class in completing their activity.
+ If you consulted any external sources, such as resources available on the World Wide Web, in completing the activity then you have cited the source. (You do not need to cite class notes or Sun/Oracle Java documentation.)
+ You are not aware of any infractions of the honour code for this activity.

> Violations of this honour code will be treated as a case of academic misconduct and will dealt with under UBC policies governing such issues. A consequence of this may be to nullify this exam for everyone that submits work for grading!

### Question: TextSearch and TweetHistory
> The skeleton source code for this question is in the package `textsearch`. You may import the provided code as a Gradle project in Eclipse.

The `TextSearch` interface allows one to search a collection of text (`String`s) to identify the number of times a particular string occurs as a substring in the text as well as to obtain a list of strings in the collection that contain the search string.

```java
public interface TextSearch {

	/*
	 * @param searchString is not null
	 *
	 * @return the number of times the given string occurs in the container
	 */
	int getNumOccurrences(String searchString);

	/*
	 * @param searchString is not null
	 *
	 * @return an ordered List of the strings in the container
	 * that include searchString as a substring.
	 * If the substring does not exist in text
	 * then an empty list is returned.
	 */
	List<String> getOccurrences(String searchString);
}
```

You have to implement the datatype `TweetHistory` that supports the `TextSearch` interface and has a few other operations.

Here are the essential operations that a `TweetHistory` supports (apart from the `TextSearch` interface):

1. **Creators**
	1. Create an empty `TweetHistory` object.

2. **Mutators**
	1. `boolean add(String tweet)`: Add a given `String` to `TweetHistory`. This method should all all strings of length <= 280 to `TweetHistory` and return `true`. If a provided string has length > 280 then it does not add it to `TweetHistory` and should return false.

3. **Observers**
	1. `String getPopularHashtag()`: Identify the most popular (frequently occurring) hashtag in `TweetHistory`. A hashtag is any substring that begins with a `#` is separated from the rest of the string by white space. It can be the first or last substring in a string or it can be a complete string itself. If the collection does not contain even one hashtag then return null. (We will treat even the single character `#` as a hashtag for this task.)

#### Test Cases

```java
@Test
public void test1() {
	TweetHistory tHistory = new TweetHistory();
	tHistory.add("hello #world");
	assertEquals("#world", tHistory.getPopularHashtag());
}

@Test
public void test2() {
	TweetHistory tHistory = new TweetHistory();
	tHistory.add("hello #world");
	tHistory.add("hello hello bye bye #cruelworld");
	assertEquals(3, tHistory.getNumOccurrences("hello"));
}

@Test
public void test3() {
	TweetHistory tHistory = new TweetHistory();
	tHistory.add("hello #world");
	tHistory.add("hello hello bye bye #cruelworld");
	assertEquals(Arrays.asList("hello hello bye bye #cruelworld"), tHistory.getOccurrences("bye"));
}

@Test
public void test4() {
	TweetHistory tHistory = new TweetHistory();
	tHistory.add("hello #world #goodmorning");
	tHistory.add("hello hello bye bye #cruelworld");
	tHistory.add("#cruelworld sic transit gloria mundi #cruelworld");
	assertEquals("#cruelworld", tHistory.getPopularHashtag());
	assertEquals(3, tHistory.getNumOccurrences("cruelworld"));
}

@Test
public void test5() {
	TweetHistory tHistory = new TweetHistory();
	tHistory.add("hello #world #goodmorning");
	tHistory.add("hello hello bye bye #cruelworld");
	assertEquals(Arrays.asList("hello #world #goodmorning", "hello hello bye bye #cruelworld"),
			tHistory.getOccurrences("hello"));
}

@Test
public void test6() {
	TweetHistory tHistory = new TweetHistory();
	tHistory.add("#floccinaucinihilipilification");
	assertEquals(Arrays.asList("#floccinaucinihilipilification"),
			tHistory.getOccurrences("floc"));
	assertEquals("#floccinaucinihilipilification", tHistory.getPopularHashtag());
}

@Test
public void test7() {
	TweetHistory tHistory = new TweetHistory();
	tHistory.add("#floccinaucinihilipilification");
	assertEquals(Arrays.asList(),
			tHistory.getOccurrences("flock"));
	assertEquals(0,
			tHistory.getNumOccurrences("flock"));
}

@Test
public void test8() {
	TweetHistory tHistory = new TweetHistory();
	tHistory.add("#");
	assertEquals("#", tHistory.getPopularHashtag());
}
```
