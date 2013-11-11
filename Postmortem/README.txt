DeathNotify provides server admins with information about where player deaths occur and who is responsible.

Permissions:

deathnotify.notifyothers: Causes this player's deaths to generate notifications
deathnotify.receivenotify: Causes this player to receive notifications

Config:

notifyOwnKill: Whether or not you can receive notifications for your own kills
notifyOwnDeath: Whether or not you can receive notifications for your own deaths
log: Whether or not to log all notifications in the console
playerFormat: How to display the notification when a player is killed by another player.
nonPlayerFormat: How to display the notification when a player is killed by a non-player.

There are four tags that can be used in the formatting entries:

{VICTIM}: The player that died
{PLAYER}: The player who killed the victim
{LOCATION}: The coordinates of the victim upon his death
{WORLD}: The world in which the victim died.

To use chat formatting, simply put an & symbol before the relevant symbol.

You must enclose the formatting string in double quote marks.

Example:

format: "&2 {VICTIM} was killed by {KILLER} at {LOCATION} in {WORLD}!"

Changelog:

v1.01:
	-Added non player death messages