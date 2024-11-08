package com.example.getpeople;

public class Person  {
    String FirstName;
    String SecondName;
    String FullName;
    int Score;
    String Likes;
    String Dislikes;

    public Person (
        String FirstName,
        String SecondName,
        String FullName,
        int Score,
        String Likes,
        String Dislikes
    ) {
        this.FirstName = FirstName;
        this.SecondName = SecondName;
        this.FullName = FullName;
        this.Score = Score;
        this.Likes = Likes;
        this.Dislikes = Dislikes;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getSecondName() {
        return SecondName;
    }
    public String getLikes() {
        return Likes;
    }
    public String getDislikes() {
        return Dislikes;
    }

    public String getFullName() {
        return FullName;
    }

    public int getScore() {
        return Score;
    }

}
