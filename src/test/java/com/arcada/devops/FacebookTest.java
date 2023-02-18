package com.arcada.devops;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class FacebookTest {

	@Test
	public void testConstructor() {
		String[] friends = {"Ville", "Mark", "Johan"};
		Facebook fb = new Facebook(friends);
		assertArrayEquals(friends, fb.getFriends());
	}

	@Test
	public void testSetFriends() {
		Facebook fb = new Facebook();

		String[] actualFriends = fb.getFriends();
		String[] expectedFriends = {"Ville", "Mark", "Johan"};

		assertEquals(0, actualFriends.length);
		assertNotEquals(expectedFriends.length, actualFriends.length);

		fb.setFriends(expectedFriends);
		assertArrayEquals(expectedFriends, fb.getFriends());

		fb.setFriends(null);
		assertArrayEquals(null, fb.getFriends());
	}

	@Test
	public void testAddFriend() {
		String[] initalFriends = {"Ville", "Mark"};
		Facebook fb = new Facebook(initalFriends);
		
		String[] actualFriends = fb.getFriends();
		String[] expectedFriends = {"Ville", "Mark", "Johan"};
		
		assertEquals(2, actualFriends.length);
		assertNotEquals(actualFriends.length, expectedFriends.length);

		fb.addFriend("Johan");
		assertArrayEquals(expectedFriends, fb.getFriends());

		fb.addFriend(null);
		assertArrayEquals(expectedFriends, fb.getFriends());
	}

	@Test
	public void testRemoveFriend() {
		String[] initalFriends = {"Ville", "Mark", "Johan"};
		Facebook fb = new Facebook(initalFriends);
		
		String[] actualFriends = fb.getFriends();
		String[] expectedFriends = {"Ville", "Johan"};

		assertEquals(3, actualFriends.length);
		assertNotEquals(actualFriends.length, expectedFriends.length);

		fb.removeFriend("Mark");
		assertArrayEquals(expectedFriends, fb.getFriends());

		fb.removeFriend("Unknown");
		assertArrayEquals(expectedFriends, fb.getFriends());

		fb.removeFriend(null);
		assertArrayEquals(expectedFriends, fb.getFriends());
	}
}