package com.workintech.twitterApi.service;
import com.workintech.twitterApi.entity.Tweet;
import com.workintech.twitterApi.entity.User;
import com.workintech.twitterApi.repository.TweetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
@AllArgsConstructor
public class TweetServiceImpl implements TweetService{

    private TweetRepository tweetRepo;

    private UserService userService;


    @Override
    public Tweet saveTweet(Tweet tweet) {
        return tweetRepo.save(tweet);
    }

    @Override
    public List<Tweet> findByAuthorId(Long authorId) {
        userService.findById(authorId);
        return tweetRepo.findByAuthorId(authorId);
    }

    @Override
    public Tweet findById(Long id) {
        Optional<Tweet> optTweet = tweetRepo.findById(id);

        return optTweet.orElseThrow(() -> new RuntimeException("Tweet not found with the given ID: " + id));
    }

    @Override
    public Tweet updateTweet(Long id, Tweet tweet) {
        Tweet existingTweet = findById(id);
        existingTweet.setContent(tweet.getContent());
        return tweetRepo.save(existingTweet);
    }

    @Override
    public void deleteTweet(Long id) {
        Tweet deletedTweet = findById(id);
        tweetRepo.delete(deletedTweet);
    }

    @Override
    public Tweet retweet(Long tweetId, Long userId) {

        // TODO Retweet edilecek orijinal tweeti bul
        Tweet originalTweet = findById(tweetId);

        // TODO Retweet yapan kullanıcıyı bul
        User tweetOwner = userService.findById(userId);

        // TODO Yeni bir Tweet nesnesi oluştur, retweet alanına orijinal tweeti set et
        Tweet newTweet = new Tweet();
        newTweet.setAuthor(tweetOwner);
        newTweet.setRetweet(originalTweet);
        newTweet.setContent(originalTweet.getContent());

        // TODO retweetCount'u artır
        originalTweet.setRetweetCount(originalTweet.getRetweetCount() + 1);

        // TODO Kaydet ve return et
        return tweetRepo.save(newTweet);
    }

    @Override
    public void deleteRetweet(Long id) {

        // TODO Retweet'i bul (id ile)
        Tweet reTweet = findById(id);

        // TODO Onun retweet alanından orijinal tweete ulaş
        Tweet originalTweet = reTweet.getRetweet();

        // TODO Orijinal tweetin retweetCount'unu azalt
        originalTweet.setRetweetCount(originalTweet.getRetweetCount() - 1);
        tweetRepo.save(originalTweet);

        // TODO Retweet'i sil
        tweetRepo.delete(reTweet);
    }
}
