# ecommerce

## Team

Cristian Robles, Stephen Webber, Victor Sullivan, Peng Chen

## Communication plan

We will communicate VIA Zoom/Remo and Slack. If we are buddy or pair programming we break out into rooms on Zoom. Whenever we bring up a topic for discussion we make sure that everyone has some kind of input and is listened to (majority vote). We collectively promise to make sure everyone’s voice is not only heard but their ideas and opinions genuinely considered. 

## Conflict plan

When there is a disagreement we put it to a majority vote. If a minority of members strongly disagrees with the majority or if we are at a stand still we bring each case to the instructor to resolve the issue. In order to create a safe environment we will adhere to Code Fellow’s code of conduct on and off campus.

### What do we do if someone is not pulling their weight? 

Daily sync-up to evaluate individual progress

Take a break. As a group. Have a group intervention. 

If it is area-expertise based, discuss doing a role-change! No biggy!

### What do we do if someone is taking over? 

If someone has a concern they can bring it to the attention of another group member. If they are in agreement it becomes a group discussion/intervention.

## Work plan: We will have a Github project where everyone is a collaborator. We will create a legend for each day on the github project that will have user story/feature issues. 

Write out issues 

Triage as a group

Assign work as a group

Group-wide notifications for file structure changes and additions.

### Work Hours

Core hours: 9-5. Core means available for synchronous communications online. 

Task-based work requirements / limits.

Lunch at same time ish.

Breaks as needed / wanted.

### Git process:  

Dev Branch

Semantically named FEATURE branches from DEV branch.

Deployed during dev process

Only working features merged to dev

Delete merged feature branches.

PRs reviewed by one person who did not work on the feature. 

Merge several times a day, when possible.
 
Master Branch

PR’s to be reviewed by the team

Merge at milestones.
 
Anything else you feel is important: Standups at 9AM via zoom.


## User Stories



As a user, I want a site to list my products for sale so that potential customers can see them and compare them to equivalent products and purchase them.
As a user, I want to browse products at the site without having to log in.
As a user, I would like to be able to add selected items to my cart.
As a user, I want to be able to view my cart after logging into the site.
As a user, I want to be able to compare between similar items offered by different sellers.
As a user I would like to be able to purchase my selected items from the seller.
As a user I would like to be able to review other sellers.



## Software Requirements

### In-
The web app will allow the users to browse the store for items posted by other users.
The web app will show a comparison between the user’s product and the product sold by a retailer.
If there are no user products matching the search, matching product listings from the retailer will be shown. And if no products exist at all as well.
The app will allow users to authenticate their login with an encrypted password.
Users should be able to see the seller’s username and rating while browsing the listing. They should be able to view the other listings and reviews from previous sold products.
Users should be able to add any item on the site to their cart once they are logged in.
Items in the shopping cart should persist but still be available for other users to view. If an item in a shopping cart gets purchased by someone else, I will not be able to purchase the item.
When browsing items, I will see whether an item has been sold or whether it is currently in someone’s cart.
An item can only be stored in a shopping cart for three days before being removed.

### Out-
This site will not be a native mobile app.
Payments and product fulfillment will be handled via a third party.
Shipping/delivery costs are specified by each user.



## Functional Requirements

A user agrees to a limitation of liability.
A user is able to set and update their profile information.
A logged-in user is able to leave a review after an item is purchased.
A guest user can search the site without logging in or creating an account.


## Data Flow

A user arrives at the site and enters search terms to browse product listings.
They find an item they are interested in. They look at the item details and the seller rating.
They select an item they would like to purchase and are notified that they must create an account to add items to their cart.
They create an account using an email address and a password.
They enter their profile details.
They add the selected item to their cart and proceed with payment.
After purchasing, they received confirmation of the order being made and processed and can leave a review for the seller.
The seller received an email notification that their item sold so that they can get it to the purchaser.
/
A user with an account is able to post a listing on their product with all given details on to the site, it will then be added onto the main site to be viewed by others and show it’s available for purchase.


## Non-Functional Requirements

Our app will encrypt passwords and sensitive user information.
Our app will not handle information such as credit cards or addresses and will instead work via a payment processor.
Our app will test that logins must be unique based on email address.
Our app will validate that items added to the database via the front-end interface are available for viewing and purchase.

