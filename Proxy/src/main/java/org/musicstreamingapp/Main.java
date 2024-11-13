package org.musicstreamingapp;

import org.musicstreamingapp.proxyserver.ProxyServer;
import org.musicstreamingapp.realserver.RealServer;
import org.musicstreamingapp.song.Song;
import org.musicstreamingapp.songservice.SongService;

import java.time.Clock;

public class Main {
    public static void main(String[] args) {

        Song wonderwall = new Song("Wonderwall",
                "(What's the Story) Morning Glory?.",
                "Oasis", 259);
        Song welcomeToTheJungle = new Song("Welcome to the Jungle",
                "Appetite for Destruction",
                "Guns 'N Roses", 271);
        Song kickstart = new Song("Kickstart My Heart",
                "Girls, Girls, Girls",
                "Mötley Crüe", 282);
        Song hendrixWatchtower = new Song("All Along the Watchtower",
                "Electric Ladyland",
                "Jimi Hendrix", 241);
        Song dylanWatchtower = new Song("All Along the Watchtower",
                "John Wesley Harding",
                "Bob Dylan", 151);
        Song wildSide = new Song("Wild Side",
                "Girls, Girls, Girls",
                "Mötley Crüe", 241);
        Song sender = new Song("Return to Sender",
                "Girls, Girls, Girls",
                "Elvis Presley", 129);
        Song pushIt = new Song("Push It",
                "Hot, Cool & Vicious",
                "Salt-n-Pepa", 271);

        SongService realServer = new RealServer();
        SongService proxyServer = new ProxyServer(realServer);

        // Data will be cached in proxy server AND stored in real server.
        proxyServer.addSong(wonderwall);
        proxyServer.addSong(welcomeToTheJungle);
        proxyServer.addSong(kickstart);
        proxyServer.addSong(hendrixWatchtower);
        proxyServer.addSong(dylanWatchtower);

        // Data stored in real server and not cached in proxy server.
        realServer.addSong(wildSide);
        realServer.addSong(sender);
        realServer.addSong(pushIt);

        Clock clock = Clock.systemUTC();
        double timeStart = clock.millis();
        proxyServer.searchById(0);
        double timeEnd = clock.millis();
        System.out.println("Search by ID when target data is cached in proxy server:");
        System.out.println("Elapsed time for proxy server: " + (timeEnd - timeStart) + " ms");

        timeStart = clock.millis();
        realServer.searchById(0);
        timeEnd = clock.millis();
        System.out.println("Elapsed time for real server: " + (timeEnd - timeStart) + " ms\n");


        System.out.println("Search by ID when target data is not located in proxy server:");
        timeStart = clock.millis();
        proxyServer.searchById(7);
        timeEnd = clock.millis();
        System.out.println("Elapsed time for proxy server: " + (timeEnd - timeStart) + " ms");
        timeStart = clock.millis();
        realServer.searchById(7);
        timeEnd = clock.millis();
        System.out.println("Elapsed time for real server: " + (timeEnd - timeStart) + " ms\n");


        System.out.println("Search by title when data is cached in proxy server:");
        timeStart = clock.millis();
        proxyServer.searchByTitle("Wonderwall");
        timeEnd = clock.millis();
        System.out.println("Elapsed time for proxy server: " + (timeEnd - timeStart) + " ms");
        timeStart = clock.millis();
        realServer.searchByTitle("Wonderwall");
        timeEnd = clock.millis();
        System.out.println("Elapsed time for real server: " + (timeEnd - timeStart) + " ms\n");


        System.out.println("Search by title when data is not located in proxy server:");
        timeStart = clock.millis();
        proxyServer.searchByTitle("Return to Sender");
        timeEnd = clock.millis();
        System.out.println("Elapsed time for proxy server: " + (timeEnd - timeStart) + " ms");
        timeStart = clock.millis();
        realServer.searchByTitle("Return to Sender");
        timeEnd = clock.millis();
        System.out.println("Elapsed time for real server: " + (timeEnd - timeStart) + " ms\n");


        System.out.println("Search by album when data is cached in proxy server:");
        timeStart = clock.millis();
        proxyServer.searchByAlbum("Girls, Girls, Girls");
        timeEnd = clock.millis();
        System.out.println("Elapsed time for proxy server: " + (timeEnd - timeStart) + " ms");
        timeStart = clock.millis();
        realServer.searchByAlbum("Girls, Girls, Girls");
        timeEnd = clock.millis();
        System.out.println("Elapsed time for real server: " + (timeEnd - timeStart) + " ms\n");

    }
}