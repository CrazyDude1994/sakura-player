package com.crazydude.sakuraplayer.models.net;

import java.util.List;

/**
 * Created by kartavtsev.s on 08.06.2015.
 */
public class ArtistInfoResponse extends ErrorResponse {

    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public class Artist extends ArtistResponse {

        private Stats stats;
        private Similar similar;
        private Tags tags;
        private Bio bio;

        public Stats getStats() {
            return stats;
        }

        public Similar getSimilar() {
            return similar;
        }

        public Bio getBio() {
            return bio;
        }

        public Tags getTags() {
            return tags;
        }

        public class Stats {

            private int listeners;
            private int plays;

            public int getListeners() {
                return listeners;
            }

            public int getPlays() {
                return plays;
            }
        }

        public class Similar {

            private List<ArtistResponse> artist;

            public List<ArtistResponse> getArtist() {
                return artist;
            }
        }

        public class Tags {

            private List<Tag> tag;

            public List<Tag> getTag() {
                return tag;
            }

            public class Tag {

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public String getUrl() {
                    return url;
                }
            }
        }

        public class Bio {

            private String published;
            private String summary;
            private String content;

            public String getPublished() {
                return published;
            }

            public String getSummary() {
                return summary;
            }

            public String getContent() {
                return content;
            }
        }
    }
}
