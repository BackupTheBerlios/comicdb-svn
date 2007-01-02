/*
 * CopyUtil.java
 *
 * Created on 2. Januar 2007, 20:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.comicdb.comicdbcore.util;

import de.comicdb.comicdbcore.bean.Comic;
import de.comicdb.comicdbcore.bean.Publisher;
import de.comicdb.comicdbcore.bean.Serie;
import de.comicdb.comicdbcore.bean.Story;
import de.comicdb.comicdbcore.bean.StoryList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dm
 */
public class CopyUtil {
    
    /** Creates a new instance of CopyUtil */
    public CopyUtil() {
    }
    
    public static Story copyStory(Story story) {
        Story nStory = new Story();
        nStory.setName(story.getName());
        nStory.setAuthor(story.getName());
        nStory.setDescription(story.getDescription());
        nStory.setPainter(story.getPainter());
        return nStory;
    }
    
    public static StoryList copyStory(StoryList storys) {
        StoryList ret = new StoryList();
        for (int i = 0; i < storys.size(); i ++) {
            ret.add(copyStory((Story)storys.get(i)));
        }
        return ret;
    }
    
    public static Comic copyComic(Comic comic) {
        Comic ret = new Comic();
        ret.setName(comic.getName());
        ret.setCondition(comic.getCondition());
        ret.setCost(comic.getCost());
        ret.setCoverdate(comic.getCoverdate());
        ret.setCoverprice(comic.getCoverprice());
        ret.setCovertype(comic.getCovertype());
        ret.setImage(comic.getImage());
        ret.setModified(new Date());
        ret.setNotes(comic.getNotes());
        ret.setNr(comic.getNr());
        ret.setPagecount(comic.getPagecount());
        ret.setPaydate(comic.getPaydate());
        ret.setPrice(comic.getPrice());
        ret.setQuantity(comic.getQuantity());
        ret.setStorys(copyStory(comic.getStorys()));
        return ret;
    }
    
    public static Serie copySerie(Serie serie) {
        Serie ret = new Serie();
        ret.setImage(serie.getImage());
        ret.setModified(new Date());
        ret.setName(serie.getName());
        List<Comic> comics = new ArrayList<Comic>();
        for (Comic c : serie.getComics()) {
            comics.add(copyComic(c));
        }
        ret.setComics(comics);
        return ret;
    }
    
    public static Publisher copyPublisher(Publisher publisher) {
        Publisher ret = new Publisher();
        ret.setImage(publisher.getImage());
        ret.setModified(new Date());
        ret.setName(publisher.getName());
        ret.setUrl(publisher.getUrl());
        List<Serie> series = new ArrayList<Serie>();
        for (Serie s : publisher.getSeries()) {
            series.add(copySerie(s));
        }
        ret.setSeries(series);
        return ret;
    }
}
