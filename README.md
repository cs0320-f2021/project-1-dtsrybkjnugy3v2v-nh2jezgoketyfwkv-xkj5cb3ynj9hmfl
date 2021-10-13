# README
## API Component:
    What makes a good API Aggregator? A good API Aggregator is fast and efficient.
    This means that the aggregator is making as few calls as possible to the APIs.

### Design Choices:
    I assumed that I knew what type of data I was parsing, so I wrote three classes with the
    fields that this data would have. I then created data classes that utilized parsed data from the
    apis into the specific class type. My most unique choice was using sets to aggregate data. 
    I utilized primary keys to determine if the set already had this data in it.

    Since I used sets, I was able to only make a single call to each api.

## K-D Tree Component:
    A K-D tree is a data structure used to store items such that that they can be sorted and found quickly 
    and accurately. In this case, we are sorting and finding users of 'Rent the Runway' who are most similar 
    to a given user or description.

### Design Choices:
    The nearest neighbors algorithm utilizes the K-D tree to find similar users efficiently. 
    The algorithm starts at the root node and compares the relevant axis of root to the same axis of 
    the target user or to the corresponding target parameter. It then recurses on one of the children 
    of the root and repeats the process until the list of nearest neighbors is populated with the most 
    similar users. 

### Questions:
    Based on the findings of the study, one can conclude that biases against certain demographics 
    can come from within the person itself in the absence of outside manipulation. The study done 
    by the National Bureau on Economic Development reveals that women often rate their abilities 
    in certain areas (particularly STEM areas) as lower than that of men, even the they perform just 
    as well. This result is probably due to deeply integrated societal expectations that come out 
    unconsciously when women are asked to rate their own abilities. Starting at a young age, expectations 
    for gender can be subliminally enforced. For example, girls are expected to play with dolls and make 
    up while boys are expected to like computers and gaming. This early exposure to technology in boys 
    not only lends itself to a more adept maneuvering of the field, but also a greater sense of confidence, 
    both of which are often absent in young girls. 

	In light of this study, one is forced to consider what other minorities are similarly influenced. 
    Other minority groups that we have considered that may also be vulnerable to self-inflicted biases 
    are people of color, such as African Americans and people of Latin American heritage, people of 
    lower socio-economic class, and/or people with disabilities. Our matchmaker’s results may consistently 
    rate these people as “of lower tier,” and perhaps consistently match them with people of “similar ability,” 
    thus perpetuating their sense of inadequacy into the future. Said teams may achieve less than their 
    “higher tier” counterparts, not because of inherent ability, but because of self-perceived limits.

    To circumvent these effects, we as designers of our reccomender should be concious
    of what kind of data we should be scouting for "nearest neighbors" and what kind of data
    should we not do that. In terms of working habits and availibility, finding neighbors
    would be ideal, but for strengths and weaknesses, differing levels should be balanced out.
    