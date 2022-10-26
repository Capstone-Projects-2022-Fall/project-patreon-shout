import React, { useState } from 'react';
import { Typography, Button, List, ListItem, ListItemText, Switch } from '@mui/material';

function UserSettings() {

    const [checked, setChecked] = useState(['discord']);

    const handleToggle = (value) => () => {
        const currentIndex = checked.indexOf(value);
        const newChecked = [...checked];

        if (currentIndex === -1) {
            newChecked.push(value);
        } else {
            newChecked.splice(currentIndex, 1);
        }

        setChecked(newChecked);
    };

    return (

        <List
            sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}
        >
            <Typography mt={1} component="div" align="center">
                <Button sx={ { borderRadius: 28 } } color="primary" type="submit" variant="contained">
                    Oauth
                </Button>
            </Typography>

            <ListItem>
                <ListItemText id="switch-list-label-discord" primary="Discord" />
                <Switch
                    edge="end"
                    onChange={handleToggle('discord')}
                    checked={checked.indexOf('discord') !== -1}
                    inputProps={{
                        'aria-labelledby': 'switch-list-label-discord',
                    }}
                />
            </ListItem>

            <ListItem>
                <ListItemText id="switch-list-label-twitter" primary="Twitter" />
                <Switch
                    edge="end"
                    onChange={handleToggle('twitter')}
                    checked={checked.indexOf('twitter') !== -1}
                    inputProps={{
                        'aria-labelledby': 'switch-list-label-twitter',
                    }}
                />
            </ListItem>

            <ListItem>
                <ListItemText id="switch-list-label-instagram" primary="Instagram" />
                <Switch
                    edge="end"
                    onChange={handleToggle('instagram')}
                    checked={checked.indexOf('instagram') !== -1}
                    inputProps={{
                        'aria-labelledby': 'switch-list-label-instagram',
                    }}
                />
            </ListItem>
        </List>
    );
}

export default UserSettings;

