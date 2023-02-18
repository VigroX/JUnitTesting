package com.arcada.devops;

import java.util.Arrays;

public class Facebook {

	private String[] friends = new String[0];

	public Facebook() {
	}

	public Facebook(String[] friends) {
		setFriends(friends);
	}

	public String[] getFriends() {
		return friends;
	}

	public void setFriends(String[] friends) {
		this.friends = friends;
	}

	public void addFriend(String friend) {
		if (friend == null || Arrays.asList(friends).contains(friend))
			return;

		String[] newFriends = Arrays.copyOf(friends, friends.length + 1);
		newFriends[newFriends.length - 1] = friend;
		friends = newFriends;
	}

	public void removeFriend(String friend) {
		if (friend == null || friends.length == 0 || !Arrays.asList(friends).contains(friend))
			return;

		String[] newFriends = new String[friends.length - 1];
		int index = 0;
		for (String f : friends) {
			if (!f.equals(friend)) {
				newFriends[index] = f;
				index++;
			}
		}
		friends = newFriends;
	}
}
