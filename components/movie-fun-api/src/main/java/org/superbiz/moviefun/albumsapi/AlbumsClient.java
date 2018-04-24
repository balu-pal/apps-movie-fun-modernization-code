package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {

    private String albumsUrl;
    private RestOperations restOperations;

    private static ParameterizedTypeReference<List<AlbumInfo>> albumsListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };

    public AlbumsClient(String moviesUrl, RestOperations restOperations) {
        this.albumsUrl = moviesUrl;
        this.restOperations = restOperations;
    }

    public void addAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumsUrl, album, AlbumInfo.class);
    }

    public AlbumInfo find(long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(albumsUrl).queryParam("id",id);
        List<AlbumInfo> albumList =restOperations.exchange(builder.toUriString(), GET, null, albumsListType).getBody();
        if(!albumList.isEmpty()){
            return albumList.get(0);
        }
        return null;
    }

    public List<AlbumInfo> getAlbums() {
        return restOperations.exchange(albumsUrl, GET, null, albumsListType).getBody();
    }


    public void deleteAlbum(AlbumInfo album) {
//        restOperations.postForEntity(albumsUrl, album, AlbumInfo.class)
//        entityManager.remove(album);
    }


    public void updateAlbum(AlbumInfo album) {

//        entityManager.merge(album);
    }
}
