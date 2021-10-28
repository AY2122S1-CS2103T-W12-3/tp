---
layout: page
title: User Guide
---

<h3><i>Welcome to the Pocket Hotel User Guide!</i></h3>

**Pocket Hotel (PH)** is a specialized contact management desktop app targeted towards hotel front office staff such as front desk staff who
frequently interact with guests.

**PH** provides a centralized location to store, organize and manage information linked to your guests and vendors employed by the hotel. **PH**
streamlines your workflow and is optimized for use via the _Command Line Interface_ (**CLI**), whilst still embodying
the benefits of a _Graphical User Interface_ (**GUI**).

This user guide serves as an entry point for users to get oriented with how **PH** operates and how you may utilize it
fully to integrate it within your hotel management system.

--------------------------------------------------------------------------------------------------------------------

## **Table of Contents**

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Quick start**
1. Ensure you have Java `11` or above installed in your Computer ([Instructions on how to check Java version](#faq)).

2. Download the latest `PH.jar` from [here](https://github.com/AY2122S1-CS2103T-W12-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for **PH**.

4. To start the application, you may either:
  1. Double-click the `PH.jar` file to boot up the app.
  2. Open up your shell terminal in the directory where **PH** resides, and run the command `java -jar PH.jar`.
     The **GUI** similar to the below should appear in a few seconds. Note how the app contains some sample data.

![Ui](images/Ui.png)

<p align="center"><i>Figure 1. Pocket Hotel GUI</i></p>
5. Type any command in the command box (Denoted by "Enter command here" text) and press Enter to execute it. e.g. typing `help` and pressing Enter will
   open the help window. Click the tab for the list that you would like to view e.g click vendor to view vendors in stored in **PH**<br>
   Some example commands you can try:

    * **`checkin`**`n/John Doe pn/X12345678F r/123 e/johnd@example.com t/Senior Citizen` : Adds a guest
      named `John Doe` to the Address Book.

    * **`deleteguest`**`pn/X12345678F` : Deletes the guest with passport number X12345678F.

    * **`clearguest`** : Deletes all guests from guest list.

    * **`addvendor`**

    * **`exit`** : Exits the app.

You may refer to the [features](#features) below for details of each command and to get familiarized with the syntax of
the commands.

--------------------------------------------------------------------------------------------------------------------


## **Features**

**PH**’s features revolve around managing your guests and vendors. For each command, a short description of its use is
given which is then followed by the format and a short example to help ensure that you have executed the command
correctly.

A quick overview of all the commands can be found in the [command summary](#command-summary).

Certain commands require parameters, which may have constraints. A quick overview of all the underlying
constraints can be found in the [parameter constraints.](#parameter-constraints-summary)

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be entered by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Parameter prefixes such as `n/` and `pn/` are special keywords that indicate a start of a parameter.

* Fields with square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Bing Cheng t/VIP` or as `n/Bing Cheng`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME pn/PASSPORT_NUMBER`, `pn/PASSPORT_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
* All fields cannot be empty
* The tag field is optional and can be omitted entirely
</div>

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### Contacts in Pocket Hotel
There are 2 types of contacts in **PH**, guests and vendors. Guests represent guests of the hotel, and vendors represent external companies employed by a hotel for services hotel. Guest are identified by their `PASSPORT_NUMBER`
and Vendors are identified by their `VENDOR_ID`. These fields are their unique identifier, and no two contacts can have the same unique identifier.

It is possible for guests and vendors to have the same unique identifier for example, `VENDOR_ID` of a vendor is 111 and `PASSPORT_NUMBER` for a guest is 111, as they represent 2 different entities.

Guests and vendors have different parameters, which can be found in the [parameter constraints table](#parameter-constraints-summary) or in the command instructions.

### Navigating between guest and vendor list
To navigate between the guest and vendor list, click the button on the **GUI** to view the different lists

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>
* Regardless of the list currently being viewed, you are able to execute any command. e.g While viewing the guest list,
  you are able to add a vendor. Toggle back to the vendor list to see the reflected changes.
</div>

# Commands

## Manage Guests

### Checking in a new guest: `checkin`

Checks in a new **guest** by adding their contact details into **PH**.

Format:
<br>`checkin pn/<PASSPORT_NUMBER> n/<NAME> e/<EMAIL> r/<ROOM_NUMBER> [t/<TAG>]`

Example:

![addGuest.png](images/addGuest.png)

* `checkin n/Bing Cheng pn/T0134568D e/bc@gmail.com r/69` , adds a new guest, Bing Cheng to **PH** and shows the new contact
  list.


* `PASSPORT_NUMBER`: Should only contain alphanumeric characters.
* `EMAIL`: A valid email address should be used.
* `ROOM_NUMBER`: Only numbers greater than 0 are valid.<br>Example
* `TAG`: An optional field, more than one can be included in the command.

[Back to Table of Contents](#table-of-contents)

### Checking in a returning guest: `checkin`
Checks in a returning **guest** into **PH**, by retrieving their details from the archive.

Format:
<br>`checkin pn/<PASSPORT_NUMBER> r/<ROOM_NUMBER>`

* `ROOM_NUMBER`: Only numbers greater than 0 are valid.<br>Example

[Back to Table of Contents](#table-of-contents)


### Checking out a guest: `checkout`
Checks out a **guest** by archiving their details and generating an invoice of all the services used by the guest.

Format:
<br>`checkout pn/<PASSPORT_NUMBER>`

Parameters:

* `PASSPORT_NUMBER`: Blank inputs are not allowed. Should only contain alphanumeric characters.

Example:

* `checkout pn/X12345678A` checks out the guest Bing Cheng, whose passport number is X12345678A.

[Back to Table of Contents](#table-of-contents)

### Editing fields of a guest : `editguest`

Edits a **guest**'s contact details. Only edits the fields that have been passed in as parameters (See example for further 
elaboration).

Format:

`editguest pn/<PASSPORT_NUMBER> <FIELD_NAME>/<NEW_FIELD_DETAILS>`

Parameters:

* `PASSPORT_NUMBER`: Blank inputs are not allowed. Should only contain alphanumeric characters.
* `NAME`: Blank inputs are not allowed.
* `EMAIL`:  Blanks inputs are not allowed. A valid email address should be used.
* `ROOM_NUMBER`: Blank inputs are not allowed. Only numbers greater than 0 are valid. 
* `TAG`: Blank inputs are not allowed. An optional field, more than one can be included in each command.

Example:
* `editguest pn/X12345678A r/123` locates the guest Bing Cheng, by his passport number X12345678A and overwrites the
  room number field with the new room number provided. All his other fields (**NAME**, **EMAIL**, **PASSPORT_NUMBER**, **TAG**)
  will remain unchanged.

* `editguest pn/X87654321A t/VIP t/LoyalGuest e/jj@mailer.com` locates the guest Jeremy, by his passport number X87654321A and overwrites his existing tags with the new tags provided, 
  and overwrites the old email field with the new email provided. All his other fields (**NAME**, **PASSPORT_NUMBER**, **ROOM_NUMBER**) will remain unchanged.

<div markdown="block" class="alert alert-info">
**:information_source: Notes:**<br>

* Existing field values will be overwritten by the newly provided field values.

* You need to specify at least one field to edit.

* You can edit more than one field at a time (See example above).
</div>

[Back to Table of Contents](#table-of-contents)

### Deleting a guest's details: `deleteguest`

Deletes an existing **guest**'s contact details from **PH**. Guests deleted can be checked in or in the archive.

Format:
<br>`deleteguest pn/<PASSPORT_NUMBER>`

Example:
`deleteguest pn/XNOO19390` deletes the guest who has passport number XNOO19390 from **PH**.

[Back to Table of Contents](#table-of-contents)

### Clear all checked in guests : `clearguest`

Deletes all guests that are currently checked in.

Format:
<br>`clearguest`

<div markdown="block" class="alert alert-info">
**:information_source: Note:**<br>
* **Archived guests** are not deleted.
</div>

[Back to Table of Contents](#table-of-contents)

### Show all guests: `listguest`

Shows all the guests checked into the hotel. Useful command to use after `filterguest`, 
it essentially removes any filter that was previously applied to the guest list. 

Format:
<br>`listguest`

<div markdown="block" class="alert alert-info">
**:information_source: Notes:**<br>
* Guests are not arranged in any particular order<br>
* Running the command when you are on the vendor list, will switch to the guest list for you.
</div>

[Back to Table of Contents](#table-of-contents)

### Charge a guest for a service: `chargeguest`

Charges a guest for a service offered by a vendor, and adds it to the invoice that will be generated upon checkout

Format:
<br>`chargeguest pn/<PASSPORT_NUMBER> vid/<VENDOR_ID>`

[Back to Table of Contents](#table-of-contents)

### Filter guest list: `filterguest`

Displays only the guests that meet your requirements, as specified by what you wrote in your filter. This helps you 
easily find and search through smaller and more manageable lists, instead of scrolling through the entire guest list.

Format:
`filterguest <FILTER_FIELD_NAME>/<FILTER_FIELD_VALUE>`

Parameters:
* `PASSPORT_NUMBER`: Should only contain alphanumeric characters.
  * The passport number specified by you must match the guest passport's number exactly to be filtered. Passport numbers are case-sensitive.
  * E.g. a guest with a `PASSPORT_NUMBER` of `SE1239182` will not be shown in your **GUI** if you run the command `filterguest pn/SE123`
* `NAME`: Should only contain alphabetical characters
  * The name specified by you simply needs to match the guest's name partially to be filtered. Names are case-insensitive.
  * E.g. a guest with a `NAME` of `Jeremy Tan` will be shown in the **GUI** if you run the command `filterguest n/jeremy` or `filterguest n/remy`
    * However, running the command `filterguest n/tan jeremy` will not filter the guest.
* `EMAIL`: A valid email address should be used.
  * The email specified by you simply needs to match the guest's email partially to be filtered. Emails are case-insensitive.
  * E.g. a guest with a `EMAIL` of `jeremytan@example.com` will be shown in the **GUI** if you run the command `filterguest e/tan`
* `ROOM_NUMBER`: Only numbers greater than 0 are valid.
  * The room number specified by you needs to match with the guest's room number partially to be filtered, however the order is important, unlike `EMAIL` and `NAME`.
  * E.g. a guest with a `ROOM_NUMBER` of `201` will be shown in the **GUI** if you run the command `filterguest r/2`
    * However, the guest will not be filtered if you run the command `filterguest r/1`. The reason for this is that hotels 
    generally have room numbers with its starting number as the floor level. Such as all hotel rooms on level 1, will 
    have their room number starting with 1. 
    * We felt that this would provide you a more useful filter, as running `filterguest r/2` would filter all the guest 
    with their room number starting with a 2, and in essence you would be filtering all the guests that are residing in 
    the second floor of your hotel. This would mean guests with `ROOM_NUMBER` that do not start with `2` but may have `2` 
    in their `ROOM_NUMBER` are not filtered. An example being `102`.
* `TAG`: An optional field, more than one can be included in the command. Tags are case-insensitive.
  * The tags specified by you need to match the guest's tags exactly to be filtered. 
  * E.g. guests with a `TAG` of `VIP` and guests with a `TAG` of `Deluxe` will be filtered if you run the command `filterguest t/vip t/deluxe`
    * Note that guest with either one of the tags gets filtered, they do not have to both tags to get filtered. The same logic applies when more than two tags are supplied by you.

Example:<br>
`filterguest n/boon r/2`, guests with a `NAME` that contains boon and have a `ROOM_NUMBER` starting with 2, will be filtered from **PH**.

<div markdown="block" class="alert alert-info">
**:information_source: Note:**<br>
* Running the `filterguest` command always applies the filter to all your guests in **PH** and not to an already filtered guest list. 
</div>

[Back to Table of Contents](#table-of-contents)

## Manage Vendors

### Adding a vendor: `addvendor`

Adds a vendors details to **PH**

Format:
<br>`addvendor vid/<VENDOR_ID> n/<NAME> e/<EMAIL> p/<PHONE_NUMBER> a/<ADDRESS> sn/<SERVICE_NAME> c/<SERVICE_COST> oh/<OPERATING HOURS> [t/TAG]`

Example:
<br>`addvendor vid/123 n/Wang's Satay e/satayMan@email.com p/84711231 a/Geylang Street 31 sn/Satay c/5 oh/1 0800-2000`

<div markdown="block" class="alert alert-info">
**:information_source: Note on OPERATING_HOUR format:**<br>
Format:
<br>`DAYS STARTTIME-ENDTIME`
<br>Monday is represented using a 1 and Sunday is represented by 7.<br>Example:<br>`1234567 0800-2359`: Monday to Sunday 8am to 11:59pm<br>`1321 0800-0900`: Monday to Wednesday 7am to 9am
</div>

* VENDOR_ID: Should only contain alphanumeric characters
* NAME: Should only contain alphabetical characters
* EMAIL: A valid email address should be used.
* PHONE_NUMBER: At least 3 digits long, should only contain numbers.
* SERVICE_NAME: Alphabetical characters and spaces are allowed.
* SERVICE_COST: Number greater than 0, will be rounded to 2 decimal places.
* TAG: An optional field, more than one can be included in each command.

[Back to Table of Contents](#table-of-contents)

### Editing fields of a vendor : `editvendor`

Edit a **vendors**' contact details. Only edits the fields that have been passed in as parameters. (See example for further elaboration).

Format:
<br>`editvendor vid/<VENDOR_ID> <FIELD_NAME>/<NEW_FIELD_DETAILS>`

Parameters:
* VENDOR_ID: Blank inputs are not allowed. Should only contain alphanumeric characters.
* NAME: Blank inputs are not allowed. Should only contain alphabetical characters.
* EMAIL: Blanks inputs are not allowed. A valid email address should be used.
* PHONE_NUMBER: Blank inputs are not allowed. At least 3 digits long, should only contain numbers.
* ADDRESS: Blank inputs are not allowed.
* SERVICE_NAME: Blank inputs are not allowed. Alphabetical characters and spaces are allowed.
* SERVICE_COST: Blank inputs are not allowed. Number greater than 0, will be rounded to 2 decimal places.
* OPERATING_HOURS: Blank inputs are not allowed. Duplicates are allowed. <br>Format: `DAYS STARTTIME-ENDTIME`<br>Monday is represented using a 1 and Sunday is represented by 7.<br>Example:<br>`1234567 0800-2359`: Monday to Sunday 8am to 11:59pm<br>`1321 0800-0900`: Monday to Wednesday 7am to 9am
* TAG: Blank inputs are not allowed. An optional field, more than one can be included in each command.

Example:
<br>`editvendor vid/123 p/99999999 e/j@mailer.com` locates the vendor, Wang's Satay, with `VENDOR_ID` 123 and overwrites the phone number
field with the new phone number provided, and the email field with the new email provided. All other fields of the vendor
(**VENDOR_ID**, **NAME**, **ADDRESS**, **SERVICE_NAME**, **SERVICE_COST**, **OPERATING_HOURS**, **TAG**) remain unchanged.

<div markdown="block" class="alert alert-info">
**:information_source: Notes:**<br>

* Existing field values will be overwritten by the newly provided field values.

* You need to specify at least one field to edit.

* You can edit more than one field at a time (See example above).

</div>

[Back to Table of Contents](#table-of-contents)

### Deleting a vendor's details: `deletevendor`

Deletes a vendors contact details from **PH**.

Format:
<br>`deletevendor vid/<VENDOR_ID>`

Example:
<br>`deletevendor vid/321`  deletes the vendor with `VENDOR_ID` 321 from **PH**.

[Back to Table of Contents](#table-of-contents)

### Clear vendor list : `clearvendor`
Deletes all vendors

Format:
<br>`clearvendor`

[Back to Table of Contents](#table-of-contents)

### Show all guests: `listvendor`

Shows all the vendors added by you that offers services to your hotel. Useful command to use after `filtervendor`,
it essentially removes any filter that was previously applied to the vendor list.

Format:
<br>`listvendor`

<div markdown="block" class="alert alert-info">
**:information_source: Notes:**<br>
* Vendors are not arranged in any particular order<br>
* Running the command when you are on the guest list, will switch to the vendor list for you.
</div>

[Back to Table of Contents](#table-of-contents)

### Filter vendor list: `filtervendor`

Displays only the vendors that meet your requirements, as specified by what you wrote in your filter. This helps you
easily find and search through smaller and more manageable lists, instead of scrolling through the entire vendor list.

Format: `filtervendor <FILTER_FIELD_NAME>/<FILTER_FIELD_VALUE>`

Parameters:
* `VENDOR_ID`: Should only contain alphanumeric characters
  * The vendor id specified by you must match the vendor's vendor id exactly to be filtered. Vendor id's are case-sensitive.
  * E.g. a vendor with a `VENDOR_ID` of `001` will not be shown in your **GUI** if you run the command `filtervendor vid/1`
* `NAME`: Should only contain alphabetical characters
  * The name specified by you simply needs to match the vendor's name partially to be filtered. Names are case-insensitive.
  * E.g. a vendor with a `NAME` of `Jeremy's Massage Parlour` will be shown in the **GUI** if you run the command `filtervendor n/jeremy` or `filtervendor n/remy`
    * However, running the command `filtervendor n/parlour massage` will not filter the vendor.
* `EMAIL`: A valid email address should be used.
  * The email specified by you simply needs to match the vendor's email partially to be filtered. Emails are case-insensitive.
  * E.g. a vendor with a `EMAIL` of `jmassage@example.com` will be shown in the **GUI** if you run the command `filtervendor e/massage`
* `ADDRESS`: Blank inputs are not allowed.
  * The address specified by you simply needs to match the vendor's address partially to be filtered. Addresses are case-insensitive.
  * E.g. a vendor with a `ADDRESS` of `123 Clementi Rd` will be shown in the **GUI** if you run the command `filtervendor a/clementi`
* `PHONE_NUMBER`: At least 3 digits long, should only contain numbers.
  * The phone number specified by you needs to match with the vendor's phone number partially to be filtered, however the order is important, unlike `EMAIL` and `NAME`.
  * E.g. a vendor with a `PHONE_NUMBER` of `93810282` will be shown in the **GUI** if you run the command `filtervendor r/938`
    * However, the vendor will not be filtered if you run the command `filtervendor r/8102`.
    * The phone number that you enter into the command will only filter the vendors with phone numbers that start with what you specified in the filter.
* `SERVICE_NAME`: Alphabetical characters and spaces are allowed.
  * The service name specified by you needs to match the vendor's service name exactly to be filtered. Service names are case-insensitive.
  * E.g. vendors with a `SERVICE_NAME` of `Massage` will be shown in the **GUI** if you run the command `filtervendor sn/massage`
    * However, the vendor will not be filtered if you run the command `filtervendor sn/mass`
* `SERVICE_COST`: Number greater than 0.
  * You may filter vendors by the exact cost or a range (using "<" or ">").
  * E.g. vendors with a `SERVICE_COST` greater than 10 will be shown in the **GUI** if you run the command `filtervendor c/>10`
  * E.g. vendors with a `SERVICE_COST` of exactly 10 will be shown in the **GUI** if you run the command `filtervendor c/10`
* `OPERATING_HOURS`: Specified in this format `DAYS STARTTIME-ENDTIME`, Timings are in 24 hr format and days are represented using numbers, where 1 represents a Monday, and a 7 represents a Sunday.
  * You may filter vendors that operate on certain days, certain days and a specified time, certain days and a specified time period, and those that are currently operating.
  * E.g. vendors with `OPERATING_HOURS` on Monday and Wednesday will be shown in the **GUI** if you run the command `filtervendor oh/13`
  * E.g. vendors with `OPERATING_HOURS` on Monday and operate on 0800 will be shown in the **GUI** if you run the command `filtervendor oh/1 0800`
  * E.g. vendors with `OPERATING_HOURS` on Tuesday and operate **anywhere** between 0800 and 1300 will be shown in the **GUI** if you run the command `filtervendor oh/2 0800-1300`
  * E.g. vendors with `OPERATING_HOURS` that are currently operating will be shown in the **GUI** if you run the command `filtervendor oh/now`
* `TAG`: An optional field, more than one can be included in the command. Tags are case-insensitive
  * The tags specified by you need to match the vendor's tags exactly to be filtered. 
  * E.g. vendors with a `TAG` of `Cheap` and vendors with a `TAG` of `Good Rating` will be filtered if you run the command `filtervendor t/cheap t/good rating`
    * Note that vendors with either one of the tags gets filtered, they do not have to both tags to get filtered. The same logic applies when more than two tags are supplied by you.
    
Example:<br>
`filtervendor sn/Food c/>10 oh/now`, vendors with the `SERVICE_NAME` food and provide the service at a `COST` greater 
than 10 and have `OPERATING_HOURS` where they are currently operating now will be filtered from **PH**

<div markdown="block" class="alert alert-info">
**:information_source: Note:**<br>
* Running the `filtervendor` command always applies the filter to all your vendors in **PH** and not to an already filtered vendor list. 
</div>

[Back to Table of Contents](#table-of-contents)

## Utility Commands

### Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help`

[Back to Table of Contents](#table-of-contents)

### Exiting the program: `exit`

Exits the program.

Format: `exit`

[Back to Table of Contents](#table-of-contents)

## Saving your Data

Your data is saved automatically to the hard disk after every command you enter. The file is saved in `.json` format,
which allows you to edit the file manually without even booting up **PH**.

[Back to Table of Contents](#table-of-contents)

### Editing your data directly

Here’s a snippet of the editable text file in JSON that is found at:
`[JAR file location]/data/addressbook.json`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Editing the json file directly should only be done by a user experienced with .json format files.
</div>

<img src="https://raw.githubusercontent.com/AY2122S1-CS2103T-W12-3/tp/master/docs/images/JsonSnippet.png" width="300">

[Back to Table of Contents](#table-of-contents)

### Guest in archive data format

The archived guests would have the room number and services field emptied in the Json file as shown in the image above.
<br> Archived guest are also not shown in the UI.

--------------------------------------------------------------------------------------------------------------------

# **Command Summary**

## Guest Command Summary

Action | Format, Examples
-------|------------------|
**checkin(new guest)** | `checkin pn/<PASSPORT_NUMBER> n/<NAME> e/<EMAIL> r/<ROOM_NUMBER> [t/<TAG>]`<br>Example: `checkin pn/T0134568D n/Bing Cheng e/bingcheng@email.com r/101 t/VIP`
**checkin(returning guest)** | `checkin pn/<PASSPORT_NUMBER> r/<ROOM_NUMBER>`<br>Example: `checkin pn/T0134568D r/101`
**checkout** | `checkout pn/<PASSPORT_NUMBER>`<br>Example: `checkout pn/T0134568D`
**editguest** | `editguest pn/<PASSPORT_NUMBER> <FIELD_NAME>/<NEW_FIELD_DETAILS>`<br>Example: `edit pn/X12345678A p/99999999`
**deleteguest** | `deleteguest pn/<PASSPORT_NUMBER>`<br>Example: `deleteguest pn/T0134568D`
**clearguest** | `clearguest`
**listguest** | `listguest`
**filterguest** | `filterguest <FILTER_FIELD_NAME>/<FILTER_FIELD_VALUE>`<br>Example: `filterguest n/Bing t/VIP`
**chargeguest** | `chargeguest pn/<PASSPORT_NUMBER> vid/<VENDOR_ID>`<br>Example: `charge pn/T0134568D vid/3`

## Vendor Command Summary


### Guest Command Summary
Action | Format, Examples
--------|------------------
**checkin(new guest)** | `checkin pn/<PASSPORT_NUMBER> n/<NAME> e/<EMAIL> r/<ROOM_NUMBER> [t/<TAG>]`<br>Example: `checkin pn/T0134568D n/Bing Cheng e/bingcheng@email.com r/101 t/VIP`
**checkin(returning guest)** | `checkin pn/<PASSPORT_NUMBER> r/<ROOM_NUMBER>`<br>Example: `checkin pn/T0134568D r/101`
**checkout** | `checkout pn/<PASSPORT_NUMBER>`<br>Example: `checkout pn/T0134568D`
**editguest** | `editguest pn/<PASSPORT_NUMBER> <FIELD_NAME>/<NEW_FIELD_DETAILS>`<br>Example: `edit pn/X12345678A p/99999999`
**deleteguest** | `deleteguest pn/<PASSPORT_NUMBER>`<br>Example: `deleteguest pn/T0134568D`
**clearguest** | `clearguest`
**listguest** | `listguest`
**filterguest** | `filterguest <FILTER_FIELD_NAME>/<FILTER_FIELD_VALUE>`<br>Example: `filterguest n/Bing t/VIP`
**chargeguest** | `chargeguest pn/<PASSPORT_NUMBER> vid/<VENDOR_ID>`<br>Example: `charge pn/T0134568D vid/3`

### Vendor Command Summary
Action | Format, Examples
-------|------------------
**addvendor** | `addvendor vid/<VENDOR_ID> n/<NAME> e/<EMAIL> p/<PHONE_NUMBER> a/<ADDRESS> sn/<SERVICE_NAME> c/<SERVICE_COST> oh/<OPERATING HOURS> [t/TAG]`<br>Example: `addvendor vid/123 n/Wang's Satay e/satayMan@email.com p/84711231 a/Geylang Street 31 sn/Satay c/5 oh/1 0800-2000`
**editvendor** | `editvendor vid/<VENDOR_ID> <FIELD_NAME>/<NEW_FIELD_DETAILS>`<br>Example: `edit vid/111 sn/Laundry`
**deletevendor** | `deletevendor vid/<VENDOR_ID>`<br>Example: `deletevendor vid/112`
**clearvendor** | `clearvendor`
**listvendor** | `listvendor`
**filtervendor** | `filtervendor vid/<VENDOR_ID> <FILTER_FIELD_NAME>/<FILTER_FIELD_VALUE>`<br>Example: `filtervendor sn/Food t/satay`
**help** | `help`
**exit** | `exit`

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
# **Parameter Constraints Section**
## **Guest Parameter Constraints Summary**

Parameter | Prefix | Constraints, Examples
----------|--------|-----------------------
**PASSPORT_NUMBER** | `pn/` | Blank inputs are not allowed.<br>Should only contain alphanumeric characters.<br>Example: `pn/X12345678A`
**NAME** | `n/` | Blank inputs are not allowed.<br>Example: `n/Bing Cheng`
**EMAIL** | `e/` | Blanks inputs are not allowed.<br>A valid email address should be used.<br>Example: `e/BingCheng@email.com`
**ROOM_NUMBER** | `r/` | Blank inputs are not allowed.<br>Only numbers greater than 0 are valid.<br>Example: `r/500`
**TAG** | `t/` | Blank inputs are not allowed.<br>An optional field, more than one can be included in each command.<br>Example: `t/Vaccinated t/Vegetarian`

## **Vendor Parameter Constraints Summary**

Parameter | Prefix | Constraints, Examples
----------|--------|-----------------------
**VENDOR_ID** | `vid/` |  Blank inputs are not allowed.<br>Should only contain alphanumeric characters.<br>Example: `vid/2131`
**NAME** | `n/` | Blank inputs are not allowed.<br>Should only contain alphabetical characters.<br>Example: `n/Wang's Satay`
**EMAIL** | `e/` | Blanks inputs are not allowed.<br>A valid email address should be used.<br>Example: `e/satayMan@email.com`
**PHONE_NUMBER** | `p/` | Blank inputs are not allowed.<br>At least 3 digits long, should only contain numbers<br>Example: `p/84711231`
**ADDRESS** | `a/` | Blank inputs are not allowed.<br>Example: `a/Geylang Street 31`
**SERVICE_NAME** | `sn/` | Blank inputs are not allowed.<br>Alphabetical characters and spaces are allowed.<br>Example: `sn/Satay`
**SERVICE_COST** | `c/` | Blank inputs are not allowed.<br>Number greater than 0, will be rounded to 2 decimal places.<br>Example: `c/5`
**OPERATING_HOURS** | `oh/` | Blank inputs are not allowed.<br>Duplicates are allowed.<br>Format: `DAYS STARTTIME-ENDTIME`<br>Monday is represented using a 1 and Sunday is represented by 7.<br>Example:<br>`1234567 0800-2359`: Monday to Sunday 8am to 11:59pm<br>`1321 0800-0900`: Monday to Wednesday 7am to 9am
**TAG** | `t/` | Blank inputs are not allowed.<br>An optional field, more than one can be included in each command.<br>Example: `t/Halal t/Free flow`

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

# **Glossary**

* **PH**: Acronym for Pocket Hotel
* **CLI**: Command line interface
* **GUI**: Graphical user interface
* **Guest**: A guest at the hotel
* **Vendor**: An external entity that a hotel uses for services
* **Unique Identifier**: An attribute that uniquely identifies a contact in the address book.
* **Archived Guests**: Guests that are not checked into the hotel and are not displayed, but whose details are stored in **PH**.
* **Unarchived guests**: Guests who are currently checked into **PH** and can be viewed in the list.

[Back to Table of Contents](#table-of-contents)


--------------------------------------------------------------------------------------------------------------------

# **FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app on your other computer and run it. Overwrite the empty .json file that is created with your old
.json file in your old computer.<br>


**Q**: How do I know if Java 11 is installed correctly on my computer?<br>
**A**: Open up your terminal and run `java --version`. The output should be java 11, if installed correctly.<br>


[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
